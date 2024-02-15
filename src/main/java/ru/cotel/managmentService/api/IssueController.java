package ru.cotel.managmentService.api;

import ru.cotel.managmentService.model.Book;
import ru.cotel.managmentService.model.Issue;
import ru.cotel.managmentService.service.IssueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Issue")
public class IssueController {

    private final IssueService service;

    @Autowired
    public IssueController(IssueService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "request for book issue", description = "запрос на выдачу книги")
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

    @GetMapping("/{id}")
    @Operation(summary = "obtaining information about the book issued by id", description = "получение информации о выданной книги по идентификатору")
    public Optional<Issue> bookIssuanceInformation(@PathVariable long id) {
        return service.bookIssuanceInformationById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "return book", description = "возврат книги")
    public Book returnBook(@PathVariable long id) {
        return service.returnAtBook(id);
    }
}
