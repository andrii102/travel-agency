package com.epam.finaltask.controller;

import com.epam.finaltask.auth.AuthenticationService;
import com.epam.finaltask.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthViewController {
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/login")
    public String loginForm() {
        return "auth/login";  // Thymeleaf template (login.html)
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "auth/register"; // Return Thymeleaf registration form
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserDTO userDTO) {
        // Register the user without authenticating them
        authenticationService.register(userDTO);
        return "redirect:/login"; // Redirect to login page after registration
    }
}
