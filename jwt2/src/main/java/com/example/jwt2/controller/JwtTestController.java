package com.example.jwt2.controller;

import com.example.jwt2.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class JwtTestController {

    private final JwtUtil jwtUtil;

    // JWT 생성 테스트
    @GetMapping("/generate")
    public String generate(@RequestParam String username) {
        return jwtUtil.generateToken(username);
    }

    // JWT 검증 테스트
    @GetMapping("/validate")
    public String validate(@RequestParam String token) {
        String username = jwtUtil.validateToken(token);
        if (username != null) {
            return "토큰 유효! username: " + username;
        } else {
            return "토큰이 유효하지 않음";
        }
    }
}
