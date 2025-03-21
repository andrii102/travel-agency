package com.epam.finaltask.restcontroller;

import com.epam.finaltask.auth.AuthenticationRequest;
import com.epam.finaltask.auth.AuthenticationService;
import com.epam.finaltask.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationRestController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        authenticationService.register(userDTO);
        return null;
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthenticationRequest authRequest) {
        System.out.println("IN LOGIN CONTROLLER");
        System.out.println("AUTHENTICATION REQUEST: " + authRequest.getUsername() + " " + authRequest.getPassword());
        return authenticationService.authenticate(authRequest);
    }
}
