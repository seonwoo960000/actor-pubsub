package com.example.springbootpubsub.application.chat;

import com.example.springbootpubsub.application.Actor;

import akka.actor.AbstractLoggingActor;

@Actor
public class ChatActor extends AbstractLoggingActor {

    private final ChatService chatService;

    public ChatActor(ChatService chatService) {
        this.chatService = chatService;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ChatMessage.class, chatService::send)
                .matchAny(this::unhandled)
                .build();
    }
}
