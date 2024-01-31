package cotel.ru.managmentService.service;

import cotel.ru.managmentService.api.IssueRequest;
import cotel.ru.managmentService.model.Book;
import cotel.ru.managmentService.model.Issue;
import cotel.ru.managmentService.model.Reader;
import cotel.ru.managmentService.repository.BookRepository;
import cotel.ru.managmentService.repository.IssueRepository;
import cotel.ru.managmentService.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class IssueService {

    @Value("${application.max-allowed-value : 1}")
    private int maxAllowedBooks;

    private final BookRepository bookRepository;
    private final IssueRepository issueRepository;
    private final ReaderRepository readerRepository;


    public Issue issue(IssueRequest request) {
        Optional<Book> book = Optional.ofNullable(bookRepository.getBookById(request.getBookId()));
        Optional<Reader> reader = Optional.ofNullable(readerRepository.getReaderById(request.getReaderId()));

        if (book.isEmpty()) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\".");
        }
        if (!book.get().isBookAvailable()) {
            throw new NoSuchElementException("Книга с идентификатором \"" + request.getReaderId() + "\" находится на выдаче.");
        }
        if (reader.isEmpty()) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\".");
        }
        if (reader.get().getCountBook() > maxAllowedBooks) {
            throw new RuntimeException("У читателя уже есть книги на руках");
        }

        Issue issue = new Issue(book.get(), reader.get());
        reader.get().setCountBook(reader.get().getCountBook() + 1);
        book.get().setBookAvailable(false);
        issueRepository.save(issue);

        return issue;
    }

    /**
     * Получение списка всех выдачей
     * @return
     */
    public List<Issue> getAllIssues() {
        return issueRepository.getAllIssues();
    }

    /**
     * Получение информации по выданной книге
     * @param id
     * @return
     */
    public Issue bookIssuanceInformationById(long id) {
        return issueRepository.bookIssuanceInformationById(id);
    }

    /**
     * Возврат книги на полку
     * @param id - идентификатор книги
     * @return -
     */
    public Book returnAtBook(long id){

        Optional<Book> book = Optional.ofNullable(bookRepository.getBookById(id));
        Optional<Issue> issue = Optional.ofNullable(issueRepository.bookIssuanceInformationById(id));
        Optional<Reader> reader = Optional.ofNullable(readerRepository.getReaderById(issue.get().getReader().getId()));

        if (book.isEmpty()) {
            log.info("Книга с данным идентификатором не найдена");
        }

        issue.get().setReturnAt(LocalDateTime.now());
        issueRepository.save(issue.get());

        reader.get().setCountBook(reader.get().getCountBook() - 1);

        return book.get();
    }
}
