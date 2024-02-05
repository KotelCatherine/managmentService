package cotel.ru.managmentService.api;

import cotel.ru.managmentService.model.Reader;
import cotel.ru.managmentService.service.ReaderService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/reader")
public class ReaderController {

    private final ReaderService repository;

    public ReaderController(ReaderService repository) {
        this.repository = repository;
    }

    /**
     * Запрос на получение информации о читателе по id
     *
     * @param id - идентификационный номер
     * @return - возвращает информацию
     */
    @GetMapping("/{id}")
    public Optional<Reader> getReaderById(@PathVariable long id) {
        return repository.getReaderById(id);
    }

    /**
     * Запрос на добавление читателя
     *
     * @param reader - информация о читателе
     * @return - возвращает добаленного читателя с его идентификационным номером
     */
    @PostMapping
    public Reader addReader(@RequestBody Reader reader) {
        return repository.saveReader(reader);
    }

    /**
     * Запрос на удаление читателя по id
     *
     * @param id - идентификатор
     * @return - выводит сообщение об успешном удалении читателя
     */
    @DeleteMapping("/{id}")
    public void deleteReader(@PathVariable long id) {
        repository.deleteReader(id);
    }

}
