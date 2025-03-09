package com.test.securitydemo.repository;

import com.test.securitydemo.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    boolean existsByUsername(String username);
    Optional<AppUser> findByUsername(String username);
}
