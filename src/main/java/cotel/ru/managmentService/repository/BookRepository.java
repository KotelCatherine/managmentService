package cotel.ru.managmentService.repository;

import cotel.ru.managmentService.model.Book;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Repository
public class BookRepository {
    private final List<Book> books;

    public BookRepository() {
        this.books = new ArrayList<>();
    }

    @PostConstruct
    public void generateData() {
        books.addAll(List.of(
                new Book("Темная башня"),
                new Book("Зерцалия"),
                new Book("Амерканские боги")
        ));
    }

    /**
     * получение всего списка книг
     *
     * @return - возвращает список книг
     */
    public List<Book> getBooks() {
        return List.copyOf(books);
    }

    /**
     * получение книги по id
     *
     * @param id - идентификатор
     * @return - возвращение информации о книге
     */
    public Book getBookById(long id) {
        return books.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Добавление книги
     *
     * @param book - информация о книге
     * @return - возвращает книгу с ее иднентификационным номером
     */
    public Book addBook(Book book) {
        books.add(book);
        return book;
    }

    /**
     * Удаление книги из списка по id
     *
     * @param id - идентификатор
     * @return - возвращает сообещние об успешном удалении
     */
    public String deleteBook(long id) {
        if (books.get((int) id) == null) {
            throw new NoSuchElementException("Книги с таким номером: \"" + id + "\" не существует");
        }

        books.removeIf(it -> it.getId() == id);
        return "Книга с идентификатором \"" + id + "\" удалена из репозитория!";
    }

}
