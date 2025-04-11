package com.epam.finaltask.controller;

import com.epam.finaltask.dto.UserDTO;
import com.epam.finaltask.dto.VoucherDTO;
import com.epam.finaltask.service.UserService;
import com.epam.finaltask.service.UserServiceImpl;
import com.epam.finaltask.service.VoucherService;
import com.epam.finaltask.service.VoucherServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    @Lazy
    VoucherServiceImpl voucherService;

    @GetMapping("/profile")
    public String profile(Model model, HttpServletRequest request) {
        model.addAttribute("currentUri", request.getRequestURI());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();
        UserDTO user = userService.getUserByUsername(username);

        List<VoucherDTO> userVouchers = voucherService.findUserVouchers(user.getId());
        model.addAttribute("userVouchers", userVouchers);

        model.addAttribute("user", user);
        return "profile";
    }
}
