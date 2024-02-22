package ru.cotel.managmentService.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.cotel.managmentService.JUnitSpringBootBase;
import ru.cotel.managmentService.model.Reader;
import ru.cotel.managmentService.repository.ReaderRepository;

class ReaderControllerTest extends JUnitSpringBootBase {
    @Autowired
    private ReaderRepository repository;
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getReaderById() {
        Reader expected = repository.save(new Reader("Cate"));

        Reader responseBody = webTestClient.get()
                .uri("/reader/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Reader.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.getId(), responseBody.getId());
        Assertions.assertEquals(expected.getName(), responseBody.getName());
        Assertions.assertEquals(expected.getCountBook(), responseBody.getCountBook());
        Assertions.assertEquals(expected.getIssue(), responseBody.getIssue());
    }
    @Test
    void getReaderByIdNotFound() {
        webTestClient.get()
                .uri("/reader/-1")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void addReader() {
        String nameReader = "Cate";

        Reader expected = webTestClient.post()
                .uri("/reader/" + nameReader)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Reader.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(expected);
        Assertions.assertEquals(expected.getName(), nameReader);
    }

    @Test
    void deleteReader() {
        Reader reader = repository.save(new Reader("Someone"));

        webTestClient.delete()
                .uri("/reader/" + reader.getId())
                .exchange()
                .expectStatus().isOk();

        Assertions.assertFalse(repository.existsById(reader.getId()));
    }
}
