package ru.cotel.managmentService.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Book")
@Schema(name = "Книга")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "Идентификатор")
    private  long id;

    @Column(name = "name")
    @Schema(name = "Имя")
    private  String name;

    @Column(name = "bookAvailable")
    @Schema(name = "Доступная книга")
    private boolean bookAvailable = true;

    @OneToOne(mappedBy = "book")
    private Issue issue;

    public Book(String name){
        this.name = name;
    }
}
