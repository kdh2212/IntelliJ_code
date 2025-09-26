package com.du.demojpa250926;

import com.du.demojpa250926.entity.Post;
import com.du.demojpa250926.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class DemoJpa250926ApplicationTests {

    @Autowired
    private PostRepository postRepository;

    @Test
    void insertPost() {
        Post post = Post.builder()
                .title("안녕")
                .content("안녕하세요")
                .author("홍길순")
                .build();
        postRepository.save(post);
    }

    @Test
    void findPostById(){
        Optional<Post> post = postRepository.findById(1L);

        System.out.println(post);


    }

    @Test
    void findAllPost(){
        List<Post> posts = postRepository.findAll();
        for(Post post : posts){
        System.out.println(post);
        }
    }

    @Test
    void updatePost(){
        Post post = postRepository.findById(2L).get();
        post.setAuthor("김한나");
        post.setTitle("감사합니다.");
        postRepository.save(post);
    }

    @Test
    void deletePost(){
        Post post = postRepository.findById(2L).get();
        postRepository.delete(post);
    }
}
