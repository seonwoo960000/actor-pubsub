package com.example.springbootpubsub.application;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.example.springbootpubsub.adapter.framework.SpringExtension;
import com.example.springbootpubsub.application.message.Message;

import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.duration.FiniteDuration;

@Component
public class MessageDispatcher {

    private final ActorSystem actorSystem;
    private final SpringExtension extension;

    public MessageDispatcher(ActorSystem actorSystem, SpringExtension extension) {
        this.actorSystem = actorSystem;
        this.extension = extension;
    }

    public void send(Message message) {
        var props = extension.get(actorSystem).props(message.actorBeanName());
        var actor = actorSystem.actorOf(props, message.actorId());
        Patterns.ask(actor, message, Timeout.durationToTimeout(FiniteDuration.create(10, TimeUnit.SECONDS)));
    }
}
