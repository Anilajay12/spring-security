package com.test.securitydemo.service;

import com.test.securitydemo.dto.Message;
import com.test.securitydemo.dto.RegistrationRequest;
import com.test.securitydemo.model.AppRole;
import com.test.securitydemo.model.AppUser;
import com.test.securitydemo.repository.AppRoleRepository;
import com.test.securitydemo.repository.AppUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final  ModelMapper modelMapper;

    @Transactional
    public Message createUser(RegistrationRequest request) {
        AppUser saveUser = modelMapper.map(request, AppUser.class);

        saveUser.setPassword(passwordEncoder.encode(saveUser.getPassword()));

        if(appUserRepository.existsByUsername(saveUser.getUsername()))
            return new Message("user already exist");

        Set<AppRole> roles = request.getRoles();
        Set<AppRole> rolesToAssign = new HashSet<>();

        // Iterate through roles and ensure they are valid and unique
        for (AppRole role : roles) {
            role.setRole(role.getRole().toUpperCase());

            AppRole existingRole = appRoleRepository.findByRole(role.getRole());

            
            if (existingRole == null) {
                AppRole savedRole = appRoleRepository.save(role);
                rolesToAssign.add(savedRole);
            } else {
                rolesToAssign.add(existingRole);
            }
        }



        log.warn("*********************");
        rolesToAssign.forEach(x -> System.out.println(x.toString()));
        log.warn("*********************");

        saveUser.setRoles(rolesToAssign);
        appUserRepository.save(saveUser);
        return new Message("registered successfully");

    }


}
