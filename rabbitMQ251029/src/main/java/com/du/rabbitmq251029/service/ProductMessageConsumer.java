package com.du.rabbitmq251029.service;


import com.du.rabbitmq251029.config.RabbitConfig;
import com.du.rabbitmq251029.entity.ProductMessage;
import com.du.rabbitmq251029.repository.ProductMessageRepository;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ProductMessageConsumer {

    private final ProductMessageRepository repository;
    private final Gson gson = new Gson();

    public ProductMessageConsumer(ProductMessageRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void receiveMessage(String jsonMessage) {
        ProductMessage message = gson.fromJson(jsonMessage, ProductMessage.class);
        repository.save(message);
        System.out.println("Saved message: " + message.getTitle());
    }
}

