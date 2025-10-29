package com.du.rabbitmq251029.service;

import com.du.rabbitmq251029.config.RabbitConfig;
import com.du.rabbitmq251029.entity.MessageEntity;
import com.du.rabbitmq251029.repository.MessageRepository;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    private final MessageRepository repository;

    public MessageConsumer(MessageRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void receiveMessage(String message) {
        repository.save(new MessageEntity(message));
        System.out.println("Saved message to DB: " + message);
    }
}

