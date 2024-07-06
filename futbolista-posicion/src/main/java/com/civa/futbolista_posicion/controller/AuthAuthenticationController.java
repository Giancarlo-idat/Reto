package com.civa.futbolista_posicion.controller;


import com.civa.futbolista_posicion.dto.auth.AuthLoginRequestDto;
import com.civa.futbolista_posicion.service.impl.UserDetailsServiceImpl;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthAuthenticationController {

    private final UserDetailsServiceImpl userDetailsService;

    public AuthAuthenticationController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthLoginRequestDto authRequest) {
        try {
            return new ResponseEntity<>(this.userDetailsService.login(authRequest), HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(("Error : " + e.getMessage()), HttpStatusCode.valueOf(400));

        }
    }
}
