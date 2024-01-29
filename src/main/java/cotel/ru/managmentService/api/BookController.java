package cotel.ru.managmentService.api;

import cotel.ru.managmentService.model.Book;
import cotel.ru.managmentService.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService repository;

    public BookController(BookService repository) {
        this.repository = repository;
    }

    /**
     * Запрос на получение списка книг
     * @return вывод списка
     */
    @GetMapping
    public List<Book> getListBook(){
        return repository.getListBooks();
    }

    /**
     * Запрос на получение описания книги по id
     * @param id - идентификационный номер книги
     * @return - вывод информации
     */
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable long id) {
        return repository.getBookById(id);
    }

    /**
     * Запрос на добавление книги
     * @param book - информация о книги
     * @return - возвращает добавленную книгу с идентификационным номером
     */
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return repository.addBook(book);
    }

    /**
     * Запрос на удаление книги по id
     * @param id - идентификационный номер
     * @return - возвращает сообщение об успешном удалении
     */
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable long id) {
        return repository.deleteBook(id);
    }
}
