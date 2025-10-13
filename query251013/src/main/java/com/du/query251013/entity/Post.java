package com.du.query251013.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// auto_increment
    private Long id;

    private String title;

    @Column(length = 1000)
    private String content;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
