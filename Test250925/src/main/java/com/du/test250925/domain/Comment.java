package com.du.test250925.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private Long id;
    private Long postId;
    private String writer;
    private String content;
    private LocalDateTime createdAt;
}
