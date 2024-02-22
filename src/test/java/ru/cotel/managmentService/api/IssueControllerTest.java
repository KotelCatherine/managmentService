package ru.cotel.managmentService.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.cotel.managmentService.JUnitSpringBootBase;
import ru.cotel.managmentService.model.Book;
import ru.cotel.managmentService.model.Issue;
import ru.cotel.managmentService.model.Reader;
import ru.cotel.managmentService.repository.BookRepository;
import ru.cotel.managmentService.repository.IssueRepository;
import ru.cotel.managmentService.repository.ReaderRepository;
import ru.cotel.managmentService.service.IssueService;

class IssueControllerTest extends JUnitSpringBootBase {

    @Autowired
    IssueRepository issueRepository;
    @Autowired
    ReaderRepository readerRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    WebTestClient webTestClient;

    @Autowired
    IssueService issueService;
    IssueRequest request = new IssueRequest();

    @Test
    void issueBook() {
        Reader reader = new Reader("Cate");
        readerRepository.save(reader);
        Book book = new Book("Dark Tower");
        bookRepository.save(book);

        request.setReaderId(reader.getId());
        request.setBookId(book.getId());

        Issue responseBody = webTestClient.post()
                .uri("/issue")
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Issue.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(responseBody.getBook().getName(), book.getName());
        Assertions.assertEquals(responseBody.getBook().isBookAvailable(), false);
        Assertions.assertEquals(responseBody.getReader().getName(), reader.getName());
    }

    @Test
    void issueBookNotFound() {
        webTestClient.post()
                .uri("/issue")
                .bodyValue(new IssueRequest())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void bookIssuanceInformation() {
        Book book = new Book("Dark Tower");
        Reader reader = new Reader("Cate");
        Issue expected = issueRepository.save(new Issue(book, reader));


        Issue responseBody = webTestClient.get()
                .uri("/issue/" + book.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Issue.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.getReader().getName(), responseBody.getReader().getName());
        Assertions.assertEquals(expected.getReader().getCountBook(), responseBody.getReader().getCountBook());
        Assertions.assertEquals(expected.getBook().getName(), responseBody.getBook().getName());
        Assertions.assertEquals(expected.getBook().isBookAvailable(), responseBody.getBook().isBookAvailable());

    }

    @Test
    void returnBook() {
        Reader reader = new Reader("Cate");
        readerRepository.save(reader);
        Book book = new Book("Dark Tower");
        bookRepository.save(book);

        request.setReaderId(reader.getId());
        request.setBookId(book.getId());

        issueService.issue(request);

        Issue responseBody = webTestClient.put()
                .uri("/issue/" + book.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Issue.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(book.getId(), responseBody.getBook().getId());
        Assertions.assertEquals(book.isBookAvailable(), responseBody.getBook().isBookAvailable());
    }
}