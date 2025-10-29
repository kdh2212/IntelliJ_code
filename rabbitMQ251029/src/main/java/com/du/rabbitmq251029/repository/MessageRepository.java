package com.du.rabbitmq251029.repository;


import com.du.rabbitmq251029.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {}

