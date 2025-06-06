package com.epam.finaltask.controller;

import com.epam.finaltask.dto.UserCredentialsDTO;
import com.epam.finaltask.dto.UserDTO;
import com.epam.finaltask.dto.VoucherDTO;
import com.epam.finaltask.service.UserServiceImpl;
import com.epam.finaltask.service.VoucherServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    @Lazy
    VoucherServiceImpl voucherService;

    @GetMapping
    public String profile(Model model, HttpServletRequest request) {
        model.addAttribute("currentUri", request.getRequestURI());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();
        UserDTO userDTO = userService.getUserByUsername(username);
        UserCredentialsDTO userCredentialsDTO = userService.getUserCredentials(username);

        List<VoucherDTO> userVouchers = voucherService.findUserVouchers(userDTO.getId());
        model.addAttribute("userVouchers", userVouchers);

        model.addAttribute("user", userCredentialsDTO);
        return "profile";
    }

    @PostMapping("/update-credantials")
    public String updateCredentials(@ModelAttribute("user") UserCredentialsDTO userCredentialsDTO,
                                    @AuthenticationPrincipal UserDetails userDetails) {

        userService.updateUserCredentials(userCredentialsDTO, userDetails.getUsername());

        return "redirect:/profile";
    }
}
