package com.david.springsecrest.controllers;

import com.david.springsecrest.payload.request.LoginDTO;
import com.david.springsecrest.payload.response.ApiResponse;
import com.david.springsecrest.services.serviceImpls.AuthServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }
    @PostMapping(path = "/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginDTO loginDto){
        return ResponseEntity.ok(ApiResponse.success("Successfully Logged in", this.authService.login(loginDto.getEmail(), loginDto.getPassword())));
    }
}
