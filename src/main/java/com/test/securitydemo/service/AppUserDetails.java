package com.test.securitydemo.service;

import com.test.securitydemo.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AppUserDetails implements UserDetails {

    private String username;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public AppUserDetails build(AppUser appUser) {
        this.username = appUser.getUsername();
        this.password = appUser.getPassword();
        this.authorities = appUser.getRoles().stream().map(auth -> new SimpleGrantedAuthority(auth.getRole())).collect(Collectors.toSet());
        return new AppUserDetails(username, password, authorities);
    }
}
