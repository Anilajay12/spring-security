package com.test.securitydemo.dto;

import com.test.securitydemo.model.AppRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    private String username;
    private String password;
    private Set<AppRole> roles;
}
