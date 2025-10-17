package com.du.newsec.controller;

import com.du.newsec.entity.User;
import com.du.newsec.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String registerForm() {
        return "register";

    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "login"; // 로그인 페이지 뷰 이동
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }


}
