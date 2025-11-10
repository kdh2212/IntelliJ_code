package com.example.product.controller;

import com.example.product.model.Product;
import com.example.product.event.ProductCreatedEvent;
import com.example.product.repository.ProductMessageRepository;
import com.example.product.repository.ProductRepository;
import com.example.product.repository.ProductEventRepository;
import com.example.product.service.EventProducer;
import com.example.product.service.ProductMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMessageRepository productMessageRepository;

    @Autowired
    private ProductMessageProducer eventProducer;

    /**
     * 🎬 모든 상품 조회
     */
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * 🎬 특정 상품 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * 🎬 상품 등록 + RabbitMQ 이벤트 발행
     */
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        // 1️⃣ 상품 DB 저장
        Product savedProduct = productRepository.save(product);

        // 2️⃣ MQ 이벤트 객체 생성
        ProductMessageProducer event = new ProductMessageProducer(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getDescription(),
                savedProduct.getPrice(),
                savedProduct.getStock()

        );

        // 3️⃣ MQ 이벤트 발행
        eventProducer.sendMessage(event);

        // 4️⃣ 이벤트 로그 DB 저장
        if (!productMessageRepository.existsByMessageId(event.getMessageId())) {
            productMessageRepository.save(event);
            System.out.println("💾 [DB] 이벤트 로그 저장 완료 → " + event.getName());
        } else {
            System.out.println("⚠️ 이미 처리된 이벤트 ID → " + event.getEventId());
        }

        System.out.println("🎬 [ProductController] 상품 등록 및 MQ 발행 완료 → " + savedProduct.getName());
        return savedProduct;
    }

    /**
     * 🎬 상품 수정
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    product.setId(id);
                    return ResponseEntity.ok(productRepository.save(product));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * 🎬 상품 삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
