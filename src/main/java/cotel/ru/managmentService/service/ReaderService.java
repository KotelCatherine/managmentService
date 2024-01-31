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

    public List<Reader> allReaders() {
        return repository.allReaders();
    }

    /**
     * Получение данных о читателе по id
     * @param id - идентификатор читателя
     * @return
     */
    public Reader getReaderById(long id) {
        return repository.getReaderById(id);
    }

    /**
     * Получение списка всех полученных книг определенному читателю
     * @param id - идентификатор читателя
     * @return
     */
    public List<Issue> getAllIssueBooksToReader(long id) {
        return repository.getAllIssueBooksToReader(id);
    }

    /**
     * Добавление читателя
     * @param reader - данные читателя
     * @return
     */
    public Reader addReader(Reader reader) {
        return repository.addReader(reader);
    }

    /**
     * Удаление информации о читателе
     * @param id - идентификатор читателя
     * @return
     */
    public void deleteReader(long id) {
        repository.deleteReader(id);
    }
}
