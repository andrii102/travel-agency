package com.epam.finaltask.controller;

import com.epam.finaltask.dto.UserDTO;
import com.epam.finaltask.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {
    @Autowired
    UserService userService;

    @GetMapping("/about")
    public String about(Model model, HttpServletRequest request) {
        model.addAttribute("currentUri", request.getRequestURI());
        return "about";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpServletRequest request) {
        model.addAttribute("currentUri", request.getRequestURI());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        System.out.println(username);
        UserDTO user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        return "profile";
    }
}