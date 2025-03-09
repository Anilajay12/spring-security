package com.test.securitydemo.repository;

import com.test.securitydemo.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppRoleRepository extends JpaRepository<AppRole, Integer> {
    AppRole findByRole(String role);
}
