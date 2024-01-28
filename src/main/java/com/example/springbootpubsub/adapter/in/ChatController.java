package com.example.springbootpubsub.adapter.in;

import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootpubsub.adapter.framework.SpringExtension;
import com.example.springbootpubsub.application.message.ChatMessage;

import akka.actor.ActorPath;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.duration.FiniteDuration;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    private final ActorSystem actorSystem;

    public ChatController(ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/test")
    public void test() throws Exception {
        ActorRef actor = actorSystem.actorOf(
                SpringExtension.SPRING_EXTENSION_PROVIDER.get(actorSystem).props("chatActor"),
                "chat");

        FiniteDuration duration = FiniteDuration.create(10, TimeUnit.SECONDS);
        Timeout timeout = Timeout.durationToTimeout(duration);

        Patterns.ask(actor, new ChatMessage("Hello"), timeout);
    }
}
