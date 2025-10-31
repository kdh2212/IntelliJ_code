package com.example.jwt2.config;

import com.example.jwt2.entity.User;
import com.example.jwt2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private boolean alreadySetup = false;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) return;

        if (userRepository.count() == 0) {
            userRepository.save(User.builder()
                    .username("admin")
                    .password(encoder.encode("1234"))
                    .role("ADMIN")
                    .build());

            userRepository.save(User.builder()
                    .username("test")
                    .password(encoder.encode("1111"))
                    .role("USER")
                    .build());

            System.out.println("✅ 기본 사용자(admin / 1234, test / 1111) 암호화 저장 완료!");
        }

        alreadySetup = true;
    }
}
