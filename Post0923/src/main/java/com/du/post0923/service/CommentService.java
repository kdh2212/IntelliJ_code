package com.du.post0923.service;


import com.du.post0923.domain.Comment;
import com.du.post0923.mapper.CommentMapper;
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
