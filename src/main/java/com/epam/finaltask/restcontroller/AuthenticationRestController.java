package com.epam.finaltask.restcontroller;

import com.epam.finaltask.auth.AuthenticationRequest;
import com.epam.finaltask.auth.AuthenticationService;
import com.epam.finaltask.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationRestController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO userDTO) {
        authenticationService.register(userDTO);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationRequest authRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authRequest));
    }
}

