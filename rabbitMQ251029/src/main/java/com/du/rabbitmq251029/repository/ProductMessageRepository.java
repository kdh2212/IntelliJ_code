package com.du.rabbitmq251029.repository;


import com.du.rabbitmq251029.entity.ProductMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductMessageRepository extends JpaRepository<ProductMessage, Long> {}


