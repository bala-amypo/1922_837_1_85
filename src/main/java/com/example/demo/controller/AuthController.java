package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest request) {
        User user = userService.registerUser(request);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), request.getPassword());
        String token = jwtTokenProvider.generateToken(authentication, user);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setUserId(user.getId());
        authResponse.setEmail(user.getEmail());
        authResponse.setRole(user.getRole());
        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully", authResponse));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userService.findByEmail(request.getEmail());
        String token = jwtTokenProvider.generateToken(authentication, user);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setUserId(user.getId());
        authResponse.setEmail(user.getEmail());
        authResponse.setRole(user.getRole());
        return ResponseEntity.ok(new ApiResponse(true, "User logged in successfully", authResponse));
    }
}