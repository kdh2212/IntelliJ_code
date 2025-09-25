package com.du.test250925.domain;

import lombok.Data;

@Data
public class PostMovie {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private String director; // 감독
    private String genre; // 장르
    private Long recommendations; // 추천
    private Long rejections; // 비추천

}
