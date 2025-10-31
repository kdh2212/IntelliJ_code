package com.example.jwt2.controller;

import com.example.jwt2.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class JwtTestController {

    private final JwtUtil jwtUtil;

    public JwtTestController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/validate")
    public ResponseEntity<Map<String, Object>> validate(@RequestParam String token) {
        String username = jwtUtil.validateTokenAndGetUsername(token);
        Map<String, Object> response = Map.of(
                "valid", username != null,
                "username", username
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/token")
    public ResponseEntity<Map<String, String>> generateToken(@RequestBody Map<String,String> body) {
        String username = body.get("username");
        // 실제 서비스에서는 여기서 사용자 검증 필요
        String token = jwtUtil.generateToken(username);
        return ResponseEntity.ok(Map.of("token", token));
    }
}

