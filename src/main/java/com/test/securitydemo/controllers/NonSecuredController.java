package com.test.securitydemo.controllers;

import com.test.securitydemo.dto.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class NonSecuredController {

    @GetMapping("/greet")
    public ResponseEntity<Message> greet(){
        return ResponseEntity
                .status(200)
                .body(new Message("Hi, This is public page"));
    }
}
