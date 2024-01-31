package cotel.ru.managmentService.repository;

import cotel.ru.managmentService.model.Issue;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


//TODO Сделать везде где необходимо проверку и вывод ошибок (Response<Entity>)

@Repository
public class IssueRepository {

    private final List<Issue> issues;

    public IssueRepository() {
        this.issues = new ArrayList<>();
    }

    /**
     * получение списка всех выдачей
     *
     * @return - возвращает список
     */
    public List<Issue> getAllIssues() {
        return List.copyOf(issues);
    }

    /**
     * Сохранение информации о выдаче книги в список
     *
     * @param issue - информация о выдаче содержит в себе: id- выдачи, bookId - номер книги, readerId- номер читателя, и время когда была выдана
     */
    public void save(Issue issue) {
        issues.add(issue);
    }

    /**
     * Информация о выданной книги по id
     *
     * @param id - идентификатор
     * @return - возвращает информацию
     */
    public Issue bookIssuanceInformationById(long id) {
        return issues.stream()
                .filter(it -> it.getId() == id)
                .findFirst()
                .orElse(null);
    }

}
