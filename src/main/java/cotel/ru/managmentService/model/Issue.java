package cotel.ru.managmentService.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Data
public class Issue {
    public static long sequence = 1L;

    private final long id;
    private final Book book;
    private final Reader reader;

    private LocalDateTime issueAt;
    private LocalDateTime returnAt;

    @JsonCreator
    public Issue(Book book, Reader reader) {
        this.id = sequence++;
        this.book = book;
        this.reader = reader;
        this.issueAt = LocalDateTime.now();
    }
}
