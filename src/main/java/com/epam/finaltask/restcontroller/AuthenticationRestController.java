package com.epam.finaltask.restcontroller;

import com.epam.finaltask.auth.AuthenticationRequest;
import com.epam.finaltask.auth.AuthenticationService;
import com.epam.finaltask.dto.UserDTO;
import com.epam.finaltask.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


//@RestController
//@RequestMapping("/api/auth")
//public class AuthenticationRestController {
//    @Autowired
//    private AuthenticationService authenticationService;
//
//    @PostMapping("/register")
//    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
//        authenticationService.register(userDTO);
//        return null;
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestBody AuthenticationRequest authRequest) {
//        System.out.println("IN LOGIN CONTROLLER");
//        System.out.println("AUTHENTICATION REQUEST: " + authRequest.getUsername() + " " + authRequest.getPassword());
//        return authenticationService.authenticate(authRequest);
//    }
//}



@Controller
public class AuthenticationRestController {
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserDTO user, RedirectAttributes redirectAttributes) {
        authenticationService.register(user);
        redirectAttributes.addFlashAttribute("message", "Registration successful! Please login.");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
    System.out.println("IN AUTHENTICATR CONTROLLER");
        String token = authenticationService.authenticate(request);
        return ResponseEntity.ok(token);
    }
}
