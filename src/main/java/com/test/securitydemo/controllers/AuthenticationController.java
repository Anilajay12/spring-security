package com.test.securitydemo.controllers;

import com.test.securitydemo.dto.Message;
import com.test.securitydemo.dto.RegistrationRequest;
import com.test.securitydemo.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
@Slf4j
public class AuthenticationController {

    @Autowired
    private AppUserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Message> register(@RequestBody RegistrationRequest request) {
        log.info("request received");
        Message response = userService.createUser(request);
        return ResponseEntity
                .status(201)
                .body(response);
    }
}
