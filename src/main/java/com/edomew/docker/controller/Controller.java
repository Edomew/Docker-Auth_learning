package com.edomew.docker.controller;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@org.springframework.stereotype.Controller
@CrossOrigin("*")
public class Controller {
    @GetMapping(value = {"/", "/home"})
    public String many(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            model
                    .addAttribute("loginStatus", true)
                    .addAttribute(
                            "role",
                            authentication.getAuthorities().stream().findFirst().isPresent()
                                    ?
                                    authentication.getAuthorities().stream().findFirst().get().getAuthority()
                                    :
                                    null
                    );
        } else {
            model.addAttribute("loginStatus", false);
        }

        return "index";
    }

}
