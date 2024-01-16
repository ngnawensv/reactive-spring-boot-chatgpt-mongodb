package com.belrose.reactivespringbootchatgptmongodb.service.impl;

// ReactiveBookServiceImpl.java
import com.belrose.reactivespringbootchatgptmongodb.model.Book;
import com.belrose.reactivespringbootchatgptmongodb.repository.ReactiveBookRepository;
import com.belrose.reactivespringbootchatgptmongodb.service.ReactiveBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveBookServiceImpl implements ReactiveBookService {

    @Autowired
    private ReactiveBookRepository reactiveBookRepository;

    @Override
    public Flux<Book> getAllBooks() {
        return reactiveBookRepository.findAll();
    }

    @Override
    public Mono<Book> getBookById(String id) {
        return reactiveBookRepository.findById(id);
    }

    @Override
    public Mono<Book> saveBook(Book book) {
        return reactiveBookRepository.save(book);
    }

    @Override
    public Mono<Void> deleteBook(String id) {
        return reactiveBookRepository.deleteById(id);
    }
}

