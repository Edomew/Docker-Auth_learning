package com.edomew.docker.service;

import com.edomew.docker.models.Role;
import com.edomew.docker.models.User;
import com.edomew.docker.repository.RoleRepository;
import com.edomew.docker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public User registerUser(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            System.out.println("Имя уже занято");
            return null;
        }
        String hashedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("ROLE_USER").orElseThrow(() -> new RuntimeException("Роли не существует"));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        return userRepository.save(new User(username, hashedPassword, userRoles));
    }
}
