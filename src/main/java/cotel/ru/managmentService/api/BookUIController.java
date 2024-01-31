package cotel.ru.managmentService.api;

import cotel.ru.managmentService.model.Book;
import cotel.ru.managmentService.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ui")
public class BookUIController {

    private final BookService service;

    public BookUIController(BookService service) {
        this.service = service;
    }

    /**
     * Запрос на получение списка книг, которые находятся на полках
     *
     * @param model
     * @return - выдает таблицу книг
     */
    @GetMapping("/books")
    public String getAllAvailableBooks(Model model) {
        List<Book> books = service.getListAvailableBooks();
        model.addAttribute("books", books);
        return "book";
    }

}
