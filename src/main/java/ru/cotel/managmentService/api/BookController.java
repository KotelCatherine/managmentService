package ru.cotel.managmentService.api;

import ru.cotel.managmentService.model.Book;
import ru.cotel.managmentService.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@Tag(name = "Book")
public class BookController {

    private final BookService repository;

    @Autowired
    public BookController(BookService repository) {
        this.repository = repository;
    }


    @GetMapping
    @Operation(summary = "get all books", description = "Получение списка со всеми книгами")
    public List<Book> getListBook() {
        return repository.getListBooks();
    }

    @GetMapping("/{id}")
    @Operation(summary = "get book by id", description = "Получение данных о книге по идентификатору")
    public Book getBookById(@PathVariable long id) {
        return repository.getBookById(id);
    }

    @PostMapping
    @Operation(summary = "add new book", description = "Добавление новой книги")
    public Book addBook(@RequestBody Book book) {
        return repository.saveBook(book);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete book by id", description = "Удаление книги по идентификатору")
    public void deleteBook(@PathVariable long id) {
        repository.deleteBook(id);
    }
}
