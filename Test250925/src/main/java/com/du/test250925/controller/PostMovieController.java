package com.du.test250925.controller;

import com.du.test250925.domain.Comment;
import com.du.test250925.domain.PostMovie;
import com.du.test250925.service.CommentService;
import com.du.test250925.service.PostMoiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Book;

@Controller
@RequiredArgsConstructor
public class PostMovieController {
    private final PostMoiveService postMoiveService;
    private final CommentService commentService; // 추가

    @GetMapping("/postmovie")
    public String postMovie(Model model) {
        model.addAttribute("postmovie", postMoiveService.findAll());
        return "list";
    }

    // 글 작성 폼
    @GetMapping("/postmovie/new")
    public String createForm() {
        return "form"; // /WEB-INF/jsp/form.jsp (작성용)
    }

    // 글 작성 처리
    @PostMapping("/postmovie")
    public String create(@RequestParam String title,
                         @RequestParam String content,
                         @RequestParam String writer,
                         @RequestParam String director,
                         @RequestParam String genre, Model model) {
        PostMovie postMovie = new PostMovie();
        postMovie.setTitle(title);
        postMovie.setContent(content);
        postMovie.setWriter(writer);
        postMovie.setDirector(director);
        postMovie.setGenre(genre);
        postMoiveService.create(postMovie);
//        model.addAttribute("postMovie", postMovie);
        return "redirect:/postmovie";
    }


    // 게시글 상세 보기
    @GetMapping("/postmovie/{id}")
    public String detail(@PathVariable Long id, Model model) {
        PostMovie postMovie = postMoiveService.findById(id);
        model.addAttribute("postMovie", postMovie);
        model.addAttribute("comments", commentService.getCommentbyPostId(id)); // 추가
        return "detail"; // /WEB-INF/jsp/detail.jsp
    }


    // 댓글 작성 폼
    @GetMapping("/postmovie/{id}/newcomment")
    public String createCommentForm(@PathVariable Long id, Model model) {
        model.addAttribute("postMovie",postMoiveService.findById(id));
        return "comment"; // /WEB-INF/jsp/form.jsp (작성용)
    }

    // 댓글 작성 처리 ( 추가 부분)
    @PostMapping("/postmovie/{id}/comments")
    public String addComment(@PathVariable Long id,
                             @RequestParam String writer,
                             @RequestParam String content) {
        Comment comment = new Comment();
        comment.setPostId(id);
        comment.setWriter(writer);
        comment.setContent(content);
        commentService.addComment(comment);
        return "redirect:/postmovie/" + id;
    }


    // 글 삭제 처리
    @PostMapping("/postmovie/{id}/delete")
    public String delete(@PathVariable Long id) {
        postMoiveService.delete(id);
        return "redirect:/postmovie";
    }


    // 글 수정 폼
    @GetMapping("/postmovie/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        PostMovie postMovie = postMoiveService.findById(id);
        model.addAttribute("postMovie", postMovie);
        return "form"; // /WEB-INF/jsp/form.jsp (수정용)
    }

    // 글 수정 처리
    @PostMapping("/postmovie/{id}/edit")
    public String update(@PathVariable Long id,
                         @RequestParam String title,
                         @RequestParam String content,
                         @RequestParam String writer,
                         @RequestParam String director,
                         @RequestParam String genre) {
        PostMovie postMovie = new PostMovie();
        postMovie.setId(id);
        postMovie.setTitle(title);
        postMovie.setContent(content);
        postMovie.setWriter(writer);
        postMovie.setDirector(director);
        postMovie.setGenre(genre);
        postMoiveService.update(postMovie);
        return "redirect:/postmovie/" + id;
    }

    // 추천 처리
    @PostMapping("/postmovie/{id}/recommend/yes")
    public String recommendMovie(@PathVariable Long id) {
        PostMovie postMovie = postMoiveService.findById(id);
        if (postMovie != null) {
            postMoiveService.updaterecommendations(postMovie);  // 추천 수 업데이트
        }
        return "redirect:/postmovie/" + id;  // 다시 상세 페이지로 리디렉션
    }

    // 비추천 처리
    @PostMapping("/postmovie/{id}/recommend/no")
    public String rejectMovie(@PathVariable Long id) {
        PostMovie postMovie = postMoiveService.findById(id);
        if (postMovie != null) {
            postMoiveService.updaterejections(postMovie);  // 비추천 수 업데이트
        }
        return "redirect:/postmovie/" + id;  // 다시 상세 페이지로 리디렉션
    }

}
