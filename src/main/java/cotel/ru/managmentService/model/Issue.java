package cotel.ru.managmentService.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Issue")
@NoArgsConstructor
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "book_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Book book;

    @JoinColumn(name = "reader_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Reader reader;

    @Column(name = "issue_at")
    private LocalDateTime issueAt;

    @Column(name = "return_at")
    private LocalDateTime returnAt;

    public Issue(Book book, Reader reader) {
        this.book = book;
        this.reader = reader;
    }
}
