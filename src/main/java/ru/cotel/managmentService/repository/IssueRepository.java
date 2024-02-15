package ru.cotel.managmentService.repository;

import ru.cotel.managmentService.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    Optional<Issue> findIssueByBookId(Long id);

    @Override
    List<Issue> findAll();
}
