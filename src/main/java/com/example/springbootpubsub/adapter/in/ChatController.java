package com.example.springbootpubsub.adapter.in;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootpubsub.application.chat.ChatMessage;
import com.example.springbootpubsub.application.MessageDispatcher;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    private final MessageDispatcher messageDispatcher;

    public ChatController(MessageDispatcher messageDispatcher) {
        this.messageDispatcher = messageDispatcher;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @PostMapping("/send")
    public void send(@RequestBody ChatMessage message){
        messageDispatcher.send(message);
    }
}
