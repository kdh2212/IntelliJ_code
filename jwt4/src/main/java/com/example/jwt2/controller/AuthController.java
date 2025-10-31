package com.example.jwt2.controller;

import com.example.jwt2.service.UserService;
import com.example.jwt2.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody Map<String,String> body) {
//        String username = body.get("username");
//        String password = body.get("password");
//        if (userService.register(username, password)) {
//            return ResponseEntity.ok("회원가입 성공!");
//        }
//        return ResponseEntity.badRequest().body("이미 존재하는 사용자입니다.");
//    }

//    @PostMapping("/login")
//    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String,String> body) {
//        String username = body.get("username");
//        String password = body.get("password");
//
//        return userService.login(username, password)
//                .map(user -> Map.of("token", jwtUtil.generateToken(user.getUsername())))
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.status(401).body(Map.of("error", "아이디 또는 비밀번호 오류")));
//    }

    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> register(@RequestBody Map<String,String> body) {
        String username = body.get("username");
        String password = body.get("password");

        if (userService.register(username, password)) {
            return ResponseEntity.ok(Map.of("message", "회원가입 성공!"));
        }
        return ResponseEntity.badRequest().body(Map.of("error", "이미 존재하는 사용자입니다."));
    }

@PostMapping("/login")
public ResponseEntity<Map<String, String>> login(@RequestBody Map<String,String> body) {
    String username = body.get("username");
    String password = body.get("password");

    return userService.login(username, password)
            .map(user -> Map.of(
                    "token", jwtUtil.generateToken(user.getUsername()),
                    "username", user.getUsername()  // ✅ username 추가
            ))
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(401).body(Map.of("error", "아이디 또는 비밀번호 오류")));
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

//    @GetMapping("/validate")
//    public ResponseEntity<Map<String, Object>> validate(@RequestParam String token) {
//        String username = jwtUtil.validateTokenAndGetUsername(token);
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("valid", username != null);
//        response.put("username", username != null ? username : ""); // null이면 빈 문자열로 처리
//
//        return ResponseEntity.ok(response);
//    }


}
