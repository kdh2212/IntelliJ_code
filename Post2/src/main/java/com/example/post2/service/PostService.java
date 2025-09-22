package com.example.post2.service;

import com.example.post2.domain.Post;
import com.example.post2.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostMapper postMapper;

    public List<Post> findAll() {
        return postMapper.findAll();
    }

    public Post findById(Long id) {
        return postMapper.findById(id);
    }

    public void create(Post post) {
        postMapper.insert(post);
    }

    public void update(Post post) {
        postMapper.update(post);
    }

    public void delete(Long id) {
        postMapper.delete(id);
    }

    public List<Post> findPage(int offset, int limit) {
        return postMapper.findPage(offset, limit);
    }

    public int count() {
        return postMapper.count();
    }

}
