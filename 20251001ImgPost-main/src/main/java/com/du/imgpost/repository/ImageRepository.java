package com.du.imgpost.repository;


import com.du.imgpost.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    // 필요 시 Post 기반으로 이미지 목록 조회 가능
    List<Image> findByPostId(Long postId);
}

