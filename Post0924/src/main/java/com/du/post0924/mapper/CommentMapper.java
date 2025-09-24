package com.du.post0924.mapper;


import com.du.post0924.domain.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("SELECT * FROM comment WHERE post_id = #{postId} ORDER BY created_at asc")
    List<Comment> findbyPostId(Long postId);

    @Insert("INSERT INTO comment(post_id, writer, content) VALUES (#{postId}, #{writer}, #{content})")
    void insert(Comment comment);
}
