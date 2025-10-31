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
                .password(encoder.encode(password))
                .build());
        return true;
    }

    public Optional<User> login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent() && encoder.matches(password, userOpt.get().getPassword())) {
            return userOpt;
        }
        return Optional.empty();
    }
}



