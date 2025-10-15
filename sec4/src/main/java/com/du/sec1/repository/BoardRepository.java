package com.du.sec1.repository;

import com.du.sec1.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByAuthorUsername(String username);
}
