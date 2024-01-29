package cotel.ru.managmentService.service;

import cotel.ru.managmentService.api.IssueRequest;
import cotel.ru.managmentService.model.Issue;
import cotel.ru.managmentService.repository.BookRepository;
import cotel.ru.managmentService.repository.IssueRepository;
import cotel.ru.managmentService.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IssueService {

    private final BookRepository bookRepository;
    private final IssueRepository issueRepository;
    private final ReaderRepository readerRepository;

    public Issue issue(IssueRequest request) {
        if (bookRepository.getBookById(request.getBookId()) == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
        }
        if (readerRepository.getReaderById(request.getReaderId()) == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
        }
        if (readerRepository.getReaderById(request.getReaderId()).getCountBook() > 3) {
            throw new RuntimeException("У читателя уже есть книги на руках");
        }

        Issue issue = new Issue(request.getBookId(), request.getReaderId());
        readerRepository.getReaderById(request.getReaderId()).setCountBook(readerRepository.getReaderById(request.getReaderId()).getCountBook() + 1);
        issueRepository.save(issue);

        return issue;
    }

    public Issue bookIssuanceInformationById(long id) {
        return issueRepository.bookIssuanceInformationById(id);
    }
}
