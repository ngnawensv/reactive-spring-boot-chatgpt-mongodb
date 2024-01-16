package com.belrose.reactivespringbootchatgptmongodb.service;

// ReactiveBookService.java
import com.belrose.reactivespringbootchatgptmongodb.model.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveBookService {

    Flux<Book> getAllBooks();

    Mono<Book> getBookById(String id);

    Mono<Book> saveBook(Book book);

    Mono<Void> deleteBook(String id);
}
