package cotel.ru.managmentService.repository;

import cotel.ru.managmentService.model.Issue;
import cotel.ru.managmentService.model.Reader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class ReaderRepository {
    private final List<Reader> readers;
    private IssueRepository issueRepository;


    public ReaderRepository() {
        this.readers = new ArrayList<>(List.of(
                new Reader("Catherine"),
                new Reader("Alex")
        ));
    }

    /**
     * Запрос на получение информации о читателе по id
     *
     * @param id - идентификатор
     * @return - возвращает информацию
     */
    public Reader getReaderById(long id) {
        if (readers.get((int) id) == null) {
            throw new NoSuchElementException("Читателя с таким номером: \"" + id + "\" не существует");
        }

        return readers.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }


    //TODO исправить вечный null
    /**
     * Должен возвращать список выданных книг данному читателю
     *
     * @param id - идентификатор читателя
     * @return --
     */
    public List<Issue> getAllIssueBooksToReader(long id) {
        List<Issue> listIssueBooks = new ArrayList<>(issueRepository.getAllIssues());
        for (Issue issue : listIssueBooks) {
            System.out.println(issue);
            if (issue.getReaderId() == id)
                listIssueBooks.add(issue);
        }
        return listIssueBooks;
    }


    /**
     * Добавление нового читателя
     * @param reader - содержит информацию о читателе
     * @return - возвращает инф-цию о чителе с идентификационным номером
     */
    public Reader addReader(Reader reader) {
        readers.add(reader);
        return reader;
    }

    /**
     * Удаление информации о читателе
     * @param id - идентификатор
     * @return - возвращает сообщение о успешном удалении
     */
    public String deleteReader(long id) {
        readers.removeIf(it -> it.getId() == id);
        return "Читатель с идентификатором \"" + id + "\" удален";
    }
}
