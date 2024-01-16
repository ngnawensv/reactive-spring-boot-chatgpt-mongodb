package com.belrose.reactivespringbootchatgptmongodb.controller;

import com.belrose.reactivespringbootchatgptmongodb.model.Book;
import com.belrose.reactivespringbootchatgptmongodb.service.ReactiveBookService;
import com.belrose.reactivespringbootchatgptmongodb.service.impl.ReactiveBookServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest(ReactiveBookController.class)
@Import(ReactiveBookServiceImpl.class) // Import the service implementation
public class ReactiveBookControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReactiveBookService reactiveBookService;

    @Test
    public void testGetAllBooks() {
        Book book1 = new Book("1", "Title1", "Author1");
        Book book2 = new Book("2", "Title2", "Author2");

        when(reactiveBookService.getAllBooks()).thenReturn(Flux.just(book1, book2));

        webTestClient.get()
                .uri("/api/books")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Book.class)
                .isEqualTo(List.of(book1, book2));

        verify(reactiveBookService, times(1)).getAllBooks();
        verifyNoMoreInteractions(reactiveBookService);
    }

    @Test
    public void testGetBookById() {
        String bookId = "1";
        Book book = new Book(bookId, "Title", "Author");

        when(reactiveBookService.getBookById(bookId)).thenReturn(Mono.just(book));

        webTestClient.get()
                .uri("/api/books/{id}", bookId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class)
                .isEqualTo(book);

        verify(reactiveBookService, times(1)).getBookById(bookId);
        verifyNoMoreInteractions(reactiveBookService);
    }

    @Test
    public void testSaveBook() throws Exception {
        Book bookToSave = new Book("1", "Title", "Author");

        when(reactiveBookService.saveBook(any())).thenReturn(Mono.just(bookToSave));

        webTestClient.post()
                .uri("/api/books")
                .bodyValue(bookToSave)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class)
                .isEqualTo(bookToSave);

        verify(reactiveBookService, times(1)).saveBook(any());
        verifyNoMoreInteractions(reactiveBookService);
    }

    @Test
    public void testDeleteBook() {
        String bookId = "1";

        when(reactiveBookService.deleteBook(bookId)).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/api/books/{id}", bookId)
                .exchange()
                .expectStatus().isOk();

        verify(reactiveBookService, times(1)).deleteBook(bookId);
        verifyNoMoreInteractions(reactiveBookService);
    }
}

