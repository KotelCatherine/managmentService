package ru.cotel.managmentService.service;

import ru.cotel.managmentService.model.Book;
import ru.cotel.managmentService.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getListBooks() {
        return repository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return repository.findById(id);
    }

    public Book saveBook(Book book) {
        return repository.save(book);
    }

    public List<Book> getListAvailableBooks() {
        return repository.findAllByBookAvailable(true);
    }

    public void deleteBook(Long id) {
        repository.deleteById(id);
    }
}
