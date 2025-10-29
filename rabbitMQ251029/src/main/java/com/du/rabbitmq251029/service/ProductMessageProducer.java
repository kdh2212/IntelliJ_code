package com.du.rabbitmq251029.service;


import com.du.rabbitmq251029.config.RabbitConfig;
import com.du.rabbitmq251029.entity.ProductMessage;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductMessageProducer {

    private final RabbitTemplate rabbitTemplate;
    private final Gson gson = new Gson();

    public ProductMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(ProductMessage message) {
        String json = gson.toJson(message);
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NAME, json);
    }
}

