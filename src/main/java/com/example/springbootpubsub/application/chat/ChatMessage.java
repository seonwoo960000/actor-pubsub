package com.example.springbootpubsub.application.chat;

import com.example.springbootpubsub.application.message.Message;

public record ChatMessage(Long id, String message) implements Message {
    @Override
    public String actorBeanName() {
        return "chatActor";
    }

    @Override
    public String actorId() {
        return id.toString();
    }
}

