package cotel.ru.managmentService.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Reader {
    private static long sequence = 1L;

    private final long id;
    private final String name;
    private int countBook;

    @JsonCreator
    public Reader(String name) {
        this(sequence++, name);
    }
}
