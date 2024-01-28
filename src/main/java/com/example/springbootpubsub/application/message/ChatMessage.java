package com.example.springbootpubsub.application.message;

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

