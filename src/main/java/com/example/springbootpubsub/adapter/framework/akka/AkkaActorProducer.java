package com.example.springbootpubsub.adapter.framework.akka;

import org.springframework.context.ApplicationContext;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;

public class AkkaActorProducer implements IndirectActorProducer {

    private final ApplicationContext applicationContext;

    private final String beanActorName;

    public AkkaActorProducer(ApplicationContext applicationContext,
                             String beanActorName) {
        this.applicationContext = applicationContext;
        this.beanActorName = beanActorName;
    }

    @Override
    public Actor produce() {
        return (Actor) applicationContext.getBean(beanActorName);
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>) applicationContext.getType(beanActorName);
    }
}