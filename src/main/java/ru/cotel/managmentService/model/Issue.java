package ru.cotel.managmentService.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Issue")
@Schema(name = "Выдача")
@NoArgsConstructor
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "Идентификатор")
    private Long id;

    @JoinColumn(name = "book_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    @Schema(name = "Идентификатор книги")
    private Book book;

    @JoinColumn(name = "reader_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    @Schema(name = "Идентификатор читателя")
    private Reader reader;

    @Column(name = "issue_at")
    @Schema(name = "Дата и время выдачи")
    private LocalDateTime issueAt;

    @Column(name = "return_at")
    @Schema(name = "Дата и время возврата")
    private LocalDateTime returnAt;

    public Issue(Book book, Reader reader) {
        this.book = book;
        this.reader = reader;
    }
}
