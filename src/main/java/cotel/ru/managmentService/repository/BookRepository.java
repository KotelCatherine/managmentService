package cotel.ru.managmentService.repository;

import cotel.ru.managmentService.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByBookAvailable(Boolean bookAvailable);
}
