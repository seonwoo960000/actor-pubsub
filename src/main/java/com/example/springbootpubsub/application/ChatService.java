package com.example.springbootpubsub.application;

import org.springframework.stereotype.Service;

import com.example.springbootpubsub.application.message.ChatMessage;

@Service
public class ChatService {

    public void send(ChatMessage message) {
        System.out.println("Message received");
    }
}

