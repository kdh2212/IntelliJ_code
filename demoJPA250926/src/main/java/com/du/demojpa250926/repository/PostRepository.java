package com.du.demojpa250926.repository;


import com.du.demojpa250926.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
