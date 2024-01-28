package com.example.springbootpubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import akka.actor.ActorSystem;

@SpringBootApplication
public class SpringBootPubSubApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPubSubApplication.class, args);
    }
}
