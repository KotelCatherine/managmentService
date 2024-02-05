package cotel.ru.managmentService.api;

import cotel.ru.managmentService.model.Book;
import cotel.ru.managmentService.model.Issue;
import cotel.ru.managmentService.service.IssueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/issue")
public class IssueController {
    @Autowired
    private IssueService service;

    /**
     * Запрос на выдачу книги
     *
     * @param request - содержит в себе id книги и id читателя
     * @return - возвращает статус о создании выдачи
     */
    @PostMapping
    public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
        log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

        final Issue issue;
        try {
            issue = service.issue(request);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(issue);
    }

    /**
     * Запрос на получении информации о выданной книге по id
     *
     * @param id - идентификационный номер
     * @return - возвращает информацию о книге
     */
    @GetMapping("/{id}")
    public Optional<Issue> bookIssuanceInformation(@PathVariable long id) {
        return service.bookIssuanceInformationById(id);
    }

    /**
     * Запрос на возврат книги
     *
     * @param id - идентификатор книги
     * @return
     */
    @PutMapping("/{id}")
    public Book returnBook(@PathVariable long id) {
        return service.returnAtBook(id);
    }
}
