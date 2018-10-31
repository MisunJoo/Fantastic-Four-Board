package project.ffboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import project.ffboard.dto.Article;
import project.ffboard.service.ArticleService;

import java.util.List;

@Controller
public class ArticleController {
    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/article/list")
    public String articleList(@RequestParam(name="categoryId")int categoryId, @RequestParam(name="start")int start, Model model) {
        model.addAttribute("articleList", articleService.getArticleList(categoryId,start));
        return "/article/list";
    }
}
