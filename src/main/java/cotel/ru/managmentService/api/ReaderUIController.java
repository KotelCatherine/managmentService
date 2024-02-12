package cotel.ru.managmentService.api;

import cotel.ru.managmentService.model.Reader;
import cotel.ru.managmentService.service.ReaderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ui")
@Tag(name = "ReaderUI")
public class ReaderUIController {
    private final ReaderService service;

    @Autowired
    public ReaderUIController(ReaderService service) {
        this.service = service;
    }


    @GetMapping("/readers")
    @Operation(summary = "get all readers", description = "получение списка всех читателей")
    private String getAllReaders(Model model) {
        List<Reader> readers = service.allReaders();
        model.addAttribute("readers", readers);
        return "reader";
    }
}
