package ru.cotel.managmentService.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.cotel.managmentService.JUnitSpringBootBase;
import ru.cotel.managmentService.model.Book;
import ru.cotel.managmentService.repository.BookRepository;

import java.util.List;


class BookControllerTest extends JUnitSpringBootBase {

    @Autowired
    WebTestClient webTestClient;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void testGetListBook() {
        bookRepository.saveAll(List.of(
                new Book("Dark Tower"),
                new Book("Harry Potter and Philosopher's stone"),
                new Book("Harry Potter and and the Chamber of secrets"),
                new Book("The Chestnut Man"),
                new Book("Good Omens")
        ));

        List<Book> expected = bookRepository.findAll();

        List<Book> responseBody = webTestClient.get()
                .uri("/book")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<Book>>() {
                })
                .returnResult()
                .getResponseBody();

        assert responseBody != null;
        Assertions.assertEquals(expected.size(), responseBody.size());
        for (Book bookResponse : responseBody) {
            boolean found = expected.stream()
                    .filter(it -> it.getId().equals(bookResponse.getId()))
                    .filter(it -> it.getName().equals(bookResponse.getName()))
                    .anyMatch(it -> it.isBookAvailable() == bookResponse.isBookAvailable());
            Assertions.assertTrue(found);
        }
    }

    @Test
    void testGetBookByIdSuccess() {
        Book expected = bookRepository.save(
                new Book("Dark Tower")
        );

        Book responseBody = webTestClient.get()
                .uri("/book/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.getId(), responseBody.getId());
        Assertions.assertEquals(expected.getName(), responseBody.getName());
        Assertions.assertEquals(expected.isBookAvailable(), responseBody.isBookAvailable());
        Assertions.assertEquals(expected.getIssue(), responseBody.getIssue());
    }

    @Test
    void testGetBookByIdNotFound() {
        webTestClient.get()
                .uri("/book/-1")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testAddBook() {
        String bookName = "The History of something";

        Book response = webTestClient.post()
                .uri("/book/" + bookName)
                .bodyValue(bookName)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getName(), bookName);
    }

    @Test
    void testDeleteBook() {
        Book book = bookRepository.save(new Book("something"));

        webTestClient.delete()
                .uri("/book/" + book.getId())
                .exchange()
                .expectStatus().isOk();

        Assertions.assertFalse(bookRepository.existsById(book.getId()));
    }
}

