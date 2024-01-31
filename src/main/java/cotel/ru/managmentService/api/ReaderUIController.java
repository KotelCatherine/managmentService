package cotel.ru.managmentService.api;

import cotel.ru.managmentService.model.Reader;
import cotel.ru.managmentService.service.ReaderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ui")
public class ReaderUIController {
    private final ReaderService service;

    public ReaderUIController(ReaderService service) {
        this.service = service;
    }

    /**
     * Запрос на получение списка читателей
     *
     * @param model
     * @return - выдает список читателей
     */
    @GetMapping("/readers")
    private String getAllReaders(Model model) {
        List<Reader> readers = service.allReaders();
        model.addAttribute("readers", readers);
        return "reader";
    }
}
