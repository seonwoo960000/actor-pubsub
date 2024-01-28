package com.example.springbootpubsub.adapter.framework.akka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import akka.actor.ActorSystem;

@Configuration
public class AkkaAutoConfiguration {
    @Bean(destroyMethod = "terminate")
    @ConditionalOnMissingBean
    public ActorSystem getActorSystem( @Value("${spring.application.name}") String name) {
        return ActorSystem.create(name);
    }
}
