package com.example.springbootpubsub.adapter.framework.akka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import akka.actor.ActorSystem;

@Configuration
public class AkkaSpringConfiguration {

    private final ApplicationContext applicationContext;

    public AkkaSpringConfiguration(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public ActorSystem actorSystem(@Value("${spring.application.name}") String name) {
        ActorSystem system = ActorSystem.create(name);
        return system;
    }
}
