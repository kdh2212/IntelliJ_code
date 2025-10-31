package com.example.jwt2.config;

import com.example.jwt2.entity.User;
import com.example.jwt2.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UserInitializer {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                userRepository.save(new User("admin", encoder.encode("1234")));
                userRepository.save(new User("test", encoder.encode("1111")));
                System.out.println("✅ 기본 사용자(admin / 1234, test / 1111) 암호화 저장 완료!");
            } else {
                System.out.println("ℹ️ 사용자 데이터가 이미 존재합니다.");
            }
        };
    }
}
