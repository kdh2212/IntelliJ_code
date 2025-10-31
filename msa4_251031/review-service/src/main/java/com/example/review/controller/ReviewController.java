package com.example.review.controller;

import com.example.review.model.Review;
import com.example.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // ✅ 전체 리뷰 조회
    @GetMapping
    public List<Review> listAll() {
        return reviewRepository.findAll();
    }

    // ✅ 상품 ID로 조회 (경로 변수 방식)
    @GetMapping("/product/{productId}")
    public List<Review> listByProduct(@PathVariable Long productId) {
        return reviewRepository.findByProductIdOrderByIdDesc(productId);
    }

    // ✅ 상품 ID로 조회 (쿼리 파라미터 방식)
    @GetMapping(params = "productId")
    public List<Review> listByProductQuery(@RequestParam Long productId) {
        return reviewRepository.findByProductIdOrderByIdDesc(productId);
    }

    // ✅ 리뷰 등록 (리뷰 추가 버튼에서 POST /api/reviews)
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        review.setCreatedAt(LocalDateTime.now()); // 작성 시간 자동 설정
        Review saved = reviewRepository.save(review);
        return ResponseEntity.ok(saved);
    }

    // (선택) ✅ 리뷰 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        if (!reviewRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        reviewRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // (선택) ✅ 리뷰 수정
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review updated) {
        return reviewRepository.findById(id)
                .map(r -> {
                    r.setAuthor(updated.getAuthor());
                    r.setContent(updated.getContent());
                    r.setRating(updated.getRating());
                    Review saved = reviewRepository.save(r);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ 단일 리뷰 조회 (수정 시 기존 데이터 로드용)
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReview(@PathVariable Long id) {
        return reviewRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
