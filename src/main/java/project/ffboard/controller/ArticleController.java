package project.ffboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.ffboard.service.ArticleService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ArticleController {
    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/article/list")
    public String list(@RequestParam("categoryid")int categoryId, @RequestParam(value = "start", defaultValue = "0")int start, Model model) {
        model.addAttribute("articleList", articleService.getArticleList(categoryId,start));
        return "/article/list";
    }

    @GetMapping("/article/read")
    public String read(@RequestParam("id") Long id, Model model){
        model.addAttribute("article", articleService.getArticle(id));
        model.addAttribute("articleContent", articleService.getArticleContent(id));
        return "/article/read";
    }

    @GetMapping("/article/write")
    public String write() {
        return "/article/write";
    }
}
