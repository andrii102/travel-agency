package com.epam.finaltask.auth;

import com.epam.finaltask.dto.UserDTO;
import com.epam.finaltask.service.UserService;
import com.epam.finaltask.token.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    public UserDTO register(UserDTO userDTO) {
        return userService.register(userDTO);
    }

    public String authenticate(AuthenticationRequest authRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authentication.isAuthenticated())
            return jwtService.generateToken(authRequest.getUsername());

        return null;
    }

}
