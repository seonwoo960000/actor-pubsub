package com.example.springbootpubsub.adapter.framework;

import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import akka.actor.ActorSystem;
import akka.cluster.Cluster;

@Configuration
public class AkkaConfiguration {

    private final ApplicationContext applicationContext;

    private final SpringExtension springExtension;

    public AkkaConfiguration(ApplicationContext applicationContext, SpringExtension springExtension) {
        this.applicationContext = applicationContext;
        this.springExtension = springExtension;
    }

    @Bean
    public ActorSystem actorSystem(@Value("${spring.application.name}") String applicationName) {
        final String shortenedApplicationName = applicationName.indexOf('.') != -1
                ? applicationName.substring(0, applicationName.indexOf('.'))
                : applicationName;
        ActorSystem system = ActorSystem.create("cluster-" + shortenedApplicationName);
        springExtension.get(system).initialize(applicationContext);
        return system;
    }

    @Bean(name = "akkaClusterThreadPoolTaskScheduler")
    public ThreadPoolTaskScheduler scheduler() {
        final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(1);
        scheduler.setThreadNamePrefix("AkkaClusterThreadPoolTaskScheduler-");
        return scheduler;
    }

    @Bean("akkaCluster")
    public Cluster akkaCluster(ActorSystem actorSystem,
                               @Value("${akka.cluster.init.delay.seconds: 300}") final Integer delaySeconds,
                               @Qualifier("akkaClusterThreadPoolTaskScheduler")
                               ThreadPoolTaskScheduler scheduler) {
        final Cluster cluster = Cluster.get(actorSystem);
        scheduler.schedule(
                () -> AkkaClusterUtils.joinToCluster(cluster, System.getenv()),
                new Date(ZonedDateTime.now().plusSeconds(delaySeconds).toInstant().toEpochMilli())
        );
        return cluster;
    }
}
