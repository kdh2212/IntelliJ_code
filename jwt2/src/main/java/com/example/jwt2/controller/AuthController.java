package com.example.jwt2.controller;

import com.example.jwt2.service.UserService;
import com.example.jwt2.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> req) {
        boolean success = userService.register(req.get("username"), req.get("password"));
        return Map.of("success", success);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> req) {
        var user = userService.login(req.get("username"), req.get("password"));
        if (user.isPresent()) {
            String token = jwtUtil.generateToken(user.get().getUsername());
            return Map.of("token", token, "username", user.get().getUsername());
        }
        return Map.of("error", "Invalid credentials");
    }

    @GetMapping("/validate")
    public Map<String, Object> validate(@RequestParam String token) {
        String username = jwtUtil.validateToken(token);
        return Map.of("valid", username != null, "username", username);
    }
}

