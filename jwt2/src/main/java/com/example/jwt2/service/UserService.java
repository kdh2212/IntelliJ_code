package com.example.jwt2.service;

import com.example.jwt2.entity.User;
import com.example.jwt2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public boolean register(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) return false;
        userRepository.save(User.builder()
                .username(username)
                .password(encoder.encode(password)) // ✅ 암호화 저장
                .build());
        return true;
    }

    public Optional<User> login(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> encoder.matches(password, user.getPassword())); // ✅ 암호 비교
    }
}


