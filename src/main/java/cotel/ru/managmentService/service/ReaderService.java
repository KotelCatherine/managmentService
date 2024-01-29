package cotel.ru.managmentService.service;

import cotel.ru.managmentService.model.Issue;
import cotel.ru.managmentService.model.Reader;
import cotel.ru.managmentService.repository.ReaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderService {

    private final ReaderRepository repository;

    public ReaderService(ReaderRepository repository) {
        this.repository = repository;
    }

    public Reader getReaderById(long id) {
        return repository.getReaderById(id);
    }

    public List<Issue> getAllIssueBooksToReader(long id) {
        return repository.getAllIssueBooksToReader(id);
    }

    public Reader addReader(Reader reader) {
        return repository.addReader(reader);
    }

    public String deleteReader(long id) {
        return repository.deleteReader(id);
    }
}
