package com.belrose.reactivespringbootchatgptmongodb.repository;

// ReactiveBookRepository.java
import com.belrose.reactivespringbootchatgptmongodb.model.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReactiveBookRepository extends ReactiveMongoRepository<Book, String> {
    // You can add custom query methods here if needed
}
