package ru.cotel.managmentService.service;

import ru.cotel.managmentService.model.Book;
import ru.cotel.managmentService.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getListBooks() {
        return repository.findAll();
    }

    public Book getBookById(long id) {
        return repository.findById(id).get();
    }

    public Book saveBook(Book book) {
        return repository.save(book);
    }

    public List<Book> getListAvailableBooks() {
        return repository.findAllByBookAvailable(true);
    }

    public void deleteBook(long id) {
        repository.deleteById(id);
    }
}
