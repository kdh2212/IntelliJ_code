package com.example.jwt2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;


    // ✅ username과 password를 받는 생성자 추가
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}

