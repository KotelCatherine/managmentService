package ru.cotel.managmentService.api;

import ru.cotel.managmentService.model.Book;
import ru.cotel.managmentService.service.BookService;
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
@Tag(name = "BookUI")
public class BookUIController {

    private final BookService service;

    @Autowired
    public BookUIController(BookService service) {
        this.service = service;
    }

    @GetMapping("/books")
    @Operation(summary = "get the list of available books", description = "Получение списка всех доступных книг")
    public String getAllAvailableBooks(Model model) {
        List<Book> books = service.getListAvailableBooks();
        model.addAttribute("books", books);
        return "book";
    }

}
