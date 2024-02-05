package cotel.ru.managmentService.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Reader")
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "count_book")
    private int countBook;

    @JoinColumn(name = "issue_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Issue issue;

    public Reader(String name) {
        this.name = name;
    }
}
