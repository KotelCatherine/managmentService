package cotel.ru.managmentService.repository;

import cotel.ru.managmentService.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByBookAvailable(Boolean bookAvailable);
}
