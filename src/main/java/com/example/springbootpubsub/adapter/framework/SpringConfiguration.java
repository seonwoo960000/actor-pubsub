package com.example.springbootpubsub.adapter.framework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import akka.actor.ActorSystem;

@Configuration
public class SpringConfiguration {

    private final ApplicationContext applicationContext;

    private final SpringExtension springExtension;

    public SpringConfiguration(ApplicationContext applicationContext, SpringExtension springExtension) {
        this.applicationContext = applicationContext;
        this.springExtension = springExtension;
    }

    @Bean
    public ActorSystem actorSystem() {
        ActorSystem system = ActorSystem.create("akka-spring-demo");
        springExtension.get(system).initialize(applicationContext);
        return system;
    }
}
