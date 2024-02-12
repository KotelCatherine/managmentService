package cotel.ru.managmentService.api;

import cotel.ru.managmentService.model.Issue;
import cotel.ru.managmentService.service.IssueService;
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
@Tag(name = "IssueUI")
public class IssueUIController {
    private final IssueService service;

    @Autowired
    public IssueUIController(IssueService service) {
        this.service = service;
    }

    @GetMapping("/issues")
    @Operation(summary = "get all issues", description = "получение списка всех выдачей")
    public String getIssues(Model model) {
        List<Issue> issues = service.getAllIssues();
        model.addAttribute("issues", issues);
        return "issue";
    }
}
