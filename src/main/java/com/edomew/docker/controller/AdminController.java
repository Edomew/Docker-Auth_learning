package com.edomew.docker.controller;

import com.edomew.docker.models.User;
import com.edomew.docker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
    private final UserRepository userRepository;

    @Autowired
    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public ModelAndView users(Model model) {
        model
                .addAttribute("listOf", "Users")
                .addAttribute("users", userRepository.findAll());
        return new ModelAndView("list");
    }
    @GetMapping("/users/{id}")
    public ModelAndView user(Model model, @PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow();
        model.addAttribute("user", user);
        return new ModelAndView("user");
    }
    @GetMapping("/edit/{id}")
    public ModelAndView edit(Model model, @PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow();
        model.addAttribute("user", user);
        return new ModelAndView("edit");
    }
    @PatchMapping("/edit")
    public ModelAndView edit(@RequestParam Long id, @RequestParam String username) {
        User user = userRepository.findById(id).orElseThrow();
        user.setUsername(username);
        userRepository.save(user);
        return new ModelAndView("redirect:/admin/users/"+user.getUserId());
    }
    @DeleteMapping("/delete")
    public ModelAndView delete(@RequestParam Long id) {
        userRepository.deleteById(id);
        return new ModelAndView("/redirect");
    }
}
