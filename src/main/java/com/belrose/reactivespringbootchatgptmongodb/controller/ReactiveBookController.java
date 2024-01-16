package com.belrose.reactivespringbootchatgptmongodb.controller;

// ReactiveBookController.java
import com.belrose.reactivespringbootchatgptmongodb.model.Book;
import com.belrose.reactivespringbootchatgptmongodb.service.ReactiveBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/books")
public class ReactiveBookController {

    @Autowired
    private ReactiveBookService reactiveBookService;

    @GetMapping
    public Flux<Book> getAllBooks() {
        return reactiveBookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Mono<Book> getBookById(@PathVariable String id) {
        return reactiveBookService.getBookById(id);
    }

    @PostMapping
    public Mono<Book> saveBook(@RequestBody Book book) {
        return reactiveBookService.saveBook(book);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteBook(@PathVariable String id) {
        return reactiveBookService.deleteBook(id);
    }
}

