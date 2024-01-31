package cotel.ru.managmentService.api;

import cotel.ru.managmentService.model.Issue;
import cotel.ru.managmentService.service.IssueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ui")
public class IssueUIController {
    private final IssueService service;

    public IssueUIController(IssueService service) {
        this.service = service;
    }

    /**
     * Запрос на получение списка всех выдачей
     *
     * @param model
     * @return - выдает таблицу с названием книги, именем читателя, дата выдачи, дата возврата(если уже вернули)
     */
    @GetMapping("/issues")
    public String getIssues(Model model) {
        List<Issue> issues = service.getAllIssues();
        model.addAttribute("issues", issues);
        return "issue";
    }
}
