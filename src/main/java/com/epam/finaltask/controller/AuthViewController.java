package com.epam.finaltask.controller;

import com.epam.finaltask.auth.AuthenticationService;
import com.epam.finaltask.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthViewController {
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username and password!");
        }
        if (logout != null) {
            model.addAttribute("logout", "You have been logged out successfully!");
        }
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
