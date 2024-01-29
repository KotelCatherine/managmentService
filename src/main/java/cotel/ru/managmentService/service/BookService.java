package cotel.ru.managmentService.service;

import cotel.ru.managmentService.model.Book;
import cotel.ru.managmentService.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getListBooks() {
        return repository.getBooks();
    }

    public Book getBookById(long id) {
        return repository.getBookById(id);
    }

    public Book addBook(Book book) {
        return repository.addBook(book);
    }

    public String deleteBook(long id) {
        return repository.deleteBook(id);
    }
}
