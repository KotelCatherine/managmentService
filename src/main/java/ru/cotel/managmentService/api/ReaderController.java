package ru.cotel.managmentService.api;

import ru.cotel.managmentService.annotation.Timer;
import ru.cotel.managmentService.model.Reader;
import ru.cotel.managmentService.service.ReaderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Timer
@RestController
@RequestMapping("/reader")
@Tag(name = "Reader")
public class ReaderController {

    private final ReaderService repository;

    @Autowired
    public ReaderController(ReaderService repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    @Operation(summary = "get reader by id", description = "получение информации о читателе")
    public Optional<Reader> getReaderById(@PathVariable Long id) {
        return repository.getReaderById(id);
    }

    @PostMapping("{name}")
    @Operation(summary = "add reader", description = "добавление читателя")
    public Reader addReader(@PathVariable String name) {
        Reader reader = new Reader(name);
        return repository.saveReader(reader);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete reader by id", description = "удаление читателя по идентификатору")
    public void deleteReader(@PathVariable Long id) {
        repository.deleteReader(id);
    }

}
