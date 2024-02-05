package cotel.ru.managmentService.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @Column(name = "name")
    private  String name;

    @Column(name = "bookAvailable")
    private boolean bookAvailable = true;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "issue_id")
    private Issue issue;

    public Book(String name){
        this.name = name;
    }
}
