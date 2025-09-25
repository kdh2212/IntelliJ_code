package com.du.test250925.service;

import com.du.test250925.domain.Comment;
import com.du.test250925.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper commentMapper;

    public List<Comment> getCommentbyPostId(Long postId){
        return commentMapper.findbyPostId(postId);
    }

    public void addComment(Comment comment){
        commentMapper.insert(comment);
    }
}
