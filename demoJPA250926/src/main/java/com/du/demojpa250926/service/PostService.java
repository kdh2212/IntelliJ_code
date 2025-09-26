package com.du.demojpa250926.service;

import com.du.demojpa250926.entity.Post;
import com.du.demojpa250926.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // 이걸 하는 이유 repository를 부를려고
public class PostService {
    private final PostRepository postRepository;
    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public Optional<Post> findById(Long id){
        return postRepository.findById(id);
    }

    public void save(Post post){
        postRepository.save(post);
    }

    public void delete(Long id){
        postRepository.deleteById(id);
    }
}
