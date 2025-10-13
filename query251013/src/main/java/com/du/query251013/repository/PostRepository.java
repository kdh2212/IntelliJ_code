package com.du.query251013.repository;

import com.du.query251013.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

//    // 제목으로 검색 ( 둘 중 하나 )
//    List<Post> findByTitleLike(String title);
//    List<Post> findByTitleContaining(String keyword);


    // 1. 제목으로 찾기
    @Query("SELECT p FROM Post p WHERE p.title = :title")
    List<Post> findByTitle(@Param("title") String title);

//    // 2. 제목에 특정 단어가 포함된 게시글 찾기 (LIKE %keyword%)
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword%")
    List<Post> findByTitleContaining(@Param("keyword") String keyword);
}
