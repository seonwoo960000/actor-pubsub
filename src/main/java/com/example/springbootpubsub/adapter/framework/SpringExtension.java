package com.example.springbootpubsub.adapter.framework;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.example.springbootpubsub.adapter.framework.akka.SpringActorProducer;

import akka.actor.Extension;
import akka.actor.Props;

@Component
public class SpringExtension implements Extension {

    private ApplicationContext applicationContext;

    public void initialize(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Props props(String actorBeanName) {
        return Props.create(SpringActorProducer.class, applicationContext, actorBeanName);
    }
}