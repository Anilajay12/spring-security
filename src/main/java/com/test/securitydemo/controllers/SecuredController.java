package com.test.securitydemo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private")
public class SecuredController {

    @GetMapping("/greet")
    public ResponseEntity<String> greet() {
        return ResponseEntity
                .status(200)
                .body("Hi, This is Secured page");
    }
}
