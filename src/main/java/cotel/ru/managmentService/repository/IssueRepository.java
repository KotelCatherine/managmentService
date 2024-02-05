package cotel.ru.managmentService.repository;

import cotel.ru.managmentService.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface IssueRepository extends JpaRepository<Issue, Long> {
    Optional<Issue> findIssueByBookId(Long id);

    List<Issue> getAll();
}
