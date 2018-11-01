package project.ffboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.ffboard.service.ArticleService;

@Controller
public class ArticleController {
    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/article/list")
    public String articleList(@RequestParam("categoryId")int categoryId, @RequestParam("start")int start, Model model) {
        System.out.println(categoryId);
        System.out.println(start);
        model.addAttribute("articleList", articleService.getArticleList(categoryId,start));
        return "/article/list";
    }
}
