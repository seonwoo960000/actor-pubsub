package com.example.springbootpubsub.application;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.springbootpubsub.application.message.ChatMessage;

import akka.actor.UntypedActor;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ChatActor extends UntypedActor {

    private final ChatService chatService;

    public ChatActor(ChatService chatService) {
        this.chatService = chatService;
    }

    @Override
    public void onReceive(Object message) {
        if (message instanceof ChatMessage) {
            chatService.send((ChatMessage) message);
        } else {
            unhandled(message);
        }
    }
}
