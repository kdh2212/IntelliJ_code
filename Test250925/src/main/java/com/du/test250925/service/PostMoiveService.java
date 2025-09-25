package com.du.test250925.service;

import com.du.test250925.domain.PostMovie;
import com.du.test250925.mapper.PostMoiveMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostMoiveService {
    private final PostMoiveMapper postMoiveMapper;


    public List<PostMovie>findAll(){
        return postMoiveMapper.findAll();
    }

    public PostMovie findById(Long id) {
        return postMoiveMapper.findById(id);
    }

    public void create(PostMovie postMovie) { postMoiveMapper.insert(postMovie); }


    public void update(PostMovie postMovie) {
        postMoiveMapper.update(postMovie);
    }

    public void delete(Long id) {
        postMoiveMapper.delete(id);
    }

    public void updaterecommendations(PostMovie postMovie) { postMoiveMapper.updateRecommendations(postMovie);}

    public void updaterejections(PostMovie postMovie) { postMoiveMapper.updateRejections(postMovie);}
}
