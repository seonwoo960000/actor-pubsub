package com.example.springbootpubsub.application.chat;

import org.springframework.stereotype.Service;

import com.example.springbootpubsub.application.chat.ChatMessage;

@Service
public class ChatService {

    public void send(ChatMessage message) {
        System.out.println("Message received");
    }
}

