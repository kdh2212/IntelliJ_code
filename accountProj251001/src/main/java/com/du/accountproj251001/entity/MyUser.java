package com.du.accountproj251001.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "my_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;

    @Column(length = 255)
    private String email;

    @Column(length = 255)
    private String password;

    @Column(length = 255)
    private String username;


}
