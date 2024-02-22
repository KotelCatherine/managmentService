package ru.cotel.managmentService.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Reader")
@Schema(name = "Читатель")
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "Идентификатор")
    private Long id;

    @Column(name = "name")
    @Schema(name = "Имя")
    private String name;

    @Column(name = "count_book")
    @Schema(name = "Количество книг")
    private int countBook;

    @OneToOne(mappedBy = "reader")
    private Issue issue;

    public Reader(String name) {
        this.name = name;
    }
}
