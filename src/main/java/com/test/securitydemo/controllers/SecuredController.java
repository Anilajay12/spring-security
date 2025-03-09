package com.test.securitydemo.controllers;

import com.test.securitydemo.dto.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured")
public class SecuredController {

    @GetMapping("/admin/greet")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Message> greetSecuredAdmin() {
        return ResponseEntity
                .status(200)
                .body(new Message("Hi, This is Secured admin page"));
    }

    @GetMapping("/user/greet")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Message> greetSecuedUsers() {
        return ResponseEntity
                .status(200)
                .body(new Message("Hi, This is Secured user page"));
    }
}
