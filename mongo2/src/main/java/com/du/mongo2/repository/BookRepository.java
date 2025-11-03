package com.du.mongo2.repository;


import com.du.mongo2.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}

