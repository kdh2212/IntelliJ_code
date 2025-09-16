package com.du.mybatis20250916;

import com.du.mybatis20250916.dao.BoardMapper;
import com.du.mybatis20250916.model.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MyBatis20250916ApplicationTests {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    void contextLoads() {
        List<Board> boards = boardMapper.findAll();
        for (Board board : boards) {
            System.out.println(board);
        }
    }

}
