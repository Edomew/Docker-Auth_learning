package com.edomew.docker.controller;

import com.edomew.docker.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/registration")
    public ModelAndView home(Authentication authentication) {
        return
                authentication != null && authentication.isAuthenticated() ?
                        new ModelAndView("redirect:/home")
                        :
                        new ModelAndView("registrationForm");
    }

    @GetMapping("/login")
    public ModelAndView loginForm(Authentication authentication) {
        return
                authentication != null && authentication.isAuthenticated() ?
                        new ModelAndView("redirect:/home")
                        :
                        new ModelAndView("/loginForm");
    }

    @PostMapping("/register")
    public String getCurrentUser(@RequestParam String username, @RequestParam String password, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            if (authService.registerUser(username, password) != null) return "redirect:/auth/login";
        }
        return null;
    }
}
