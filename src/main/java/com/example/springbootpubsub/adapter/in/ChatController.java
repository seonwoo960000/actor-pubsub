package com.example.springbootpubsub.adapter.in;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

}
