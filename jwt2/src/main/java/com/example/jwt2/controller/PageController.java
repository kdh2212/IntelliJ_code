package com.example.jwt2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/jwt-test")
    public String jwtTestPage() {
        // templates/jwt-test.html 파일을 찾아서 렌더링
        return "jwt-test";
    }

    @GetMapping("/jwt-login-test")
    public String jwtLoginTestPage() {
        return "jwt-login-test";
    }

    @GetMapping("/jwt-visual")
    public String jwtVisualPage() {
        return "jwt-jwt-visualizer";
    }
}

