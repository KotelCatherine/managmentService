package cotel.ru.managmentService.service;

import cotel.ru.managmentService.model.Reader;
import cotel.ru.managmentService.repository.ReaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReaderService {

    private final ReaderRepository repository;

    public ReaderService(ReaderRepository repository) {
        this.repository = repository;
    }

    public List<Reader> allReaders() {
        return repository.findAll();
    }

    /**
     * Получение данных о читателе по id
     * @param id - идентификатор читателя
     * @return
     */
    public Optional<Reader> getReaderById(long id) {
        return repository.findById(id);
    }

    /**
     * Добавление читателя
     * @param reader - данные читателя
     * @return
     */
    public Reader saveReader(Reader reader) {
        return repository.save(reader);
    }

    /**
     * Удаление информации о читателе
     * @param id - идентификатор читателя
     * @return
     */
    public void deleteReader(long id) {
        repository.deleteById(id);
    }
}
