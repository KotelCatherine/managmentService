package cotel.ru.managmentService.api;

import cotel.ru.managmentService.model.Issue;
import cotel.ru.managmentService.model.Reader;
import cotel.ru.managmentService.service.ReaderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Reader getReaderById(@PathVariable long id) {
        return repository.getReaderById(id);
    }


    //TODO не доделан ибо вечный null

    /**
     * Запрос на получение списка выдачей читателю
     *
     * @param id - идентификационный номер читателя
     * @return - возращает список выдачей
     */
    @GetMapping("/{id}/issue")
    public List<Issue> getAllIssueBooksToReader(@PathVariable long id) {
        return repository.getAllIssueBooksToReader(id);
    }

    /**
     * Запрос на добавление читателя
     *
     * @param reader - информация о читателе
     * @return - возвращает добаленного читателя с его идентификационным номером
     */
    @PostMapping
    public Reader addReader(@RequestBody Reader reader) {
        return repository.addReader(reader);
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
