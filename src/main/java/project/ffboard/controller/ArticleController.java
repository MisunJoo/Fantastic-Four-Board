package project.ffboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.ffboard.dto.Article;
import project.ffboard.dto.ArticleContent;
import project.ffboard.service.ArticleService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ArticleController {
    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    //게시판 글 목록 가져오기
    @GetMapping("/article/list")
    public String list(@RequestParam("categoryid")int categoryId, @RequestParam(value = "start", defaultValue = "0")int start, Model model) {
        model.addAttribute("articleList", articleService.getArticleList(categoryId,start));
        model.addAttribute("categoryId", categoryId);
        return "/article/list";
    }

    //게시판 검색된 글 목록 가져오기
    @PostMapping("/article/search")
    public String list(@RequestParam("categoryId")int categoryId, @RequestParam(value = "start", defaultValue = "0")int start,
                       @RequestParam("searchType") String searchType, @RequestParam("searchWord") String searchWord, Model model) {
        model.addAttribute("articleList", articleService.getArticleList(categoryId,start,searchType,searchWord));
        model.addAttribute("categoryId", categoryId);
        return "/article/list";
    }

    //게시판 글 읽기
    @GetMapping("/article/read")
    public String read(@RequestParam("id")Long id, Model model){
        model.addAttribute("article", articleService.getArticle(id));
        model.addAttribute("articleContent", articleService.getArticleContent(id));
        return "/article/read";
    }

    //게시판 글 쓰기
    @GetMapping("/article/write")
    public String write(@RequestParam("categoryid") int categoryId, Model model) {
        model.addAttribute("categoryId", categoryId);
        return "/article/write";
    }

    @PostMapping("/article/write")
    public String write(Article article, ArticleContent articleContent,
                        HttpServletRequest request, Model model) {
        article.setGroupSeq(0);
        article.setDepthLevel(0);
        article.setIpAddress(request.getRemoteAddr());
//        article.setHit(0);

        //회원정보 관련된 set은 세션을 구현한 후에 넣어주어야 함
        article.setMemberId(1L);
        article.setNickName("관리자");

        articleService.addArticle(article,articleContent);

        model.addAttribute("categoryid", article.getCategoryId());
        return "redirect:/article/list";
    }

    //게시판 답글달기
    @GetMapping("/article/reply")
    public String reply(@RequestParam("id")Long id,Model model) {
        model.addAttribute("parentId", id);
        //model.addAttribute("categoryid", categoryId);
        return "/article/reply";
    }

    @PostMapping("/article/reply")
    public String reply(Article article, ArticleContent articleContent,
                        @RequestParam("parentId")Long parentId, HttpServletRequest request, Model model) {
        //계층형을 위한 처리, 부모의 groupId를받고, groupseq와 depthlevel을 부모보다 1크게한다.
        Article parentArticle = articleService.getArticle(parentId);
        article.setGroupId(parentArticle.getGroupId());
        article.setGroupSeq(parentArticle.getGroupSeq());
        article.setDepthLevel(parentArticle.getDepthLevel());

        article.setCategoryId(parentArticle.getCategoryId());
        article.setIpAddress(request.getRemoteAddr());
//        article.setHit(0);

        //회원정보 관련된 set은 세션을 구현한 후에 넣어주어야 함
        article.setMemberId(1L);
        article.setNickName("관리자");

        articleService.addArticle(article,articleContent);
        model.addAttribute("categoryid", article.getCategoryId());
        return "redirect:/article/list";
    }

    //게시판 글 수정
    @GetMapping("/article/update")
    public String update(@RequestParam("id")Long id, Model model) {
        model.addAttribute("article",articleService.getArticle(id));
        model.addAttribute("articleContent", articleService.getArticleContent(id));
        return "/article/update";
    }

    @PostMapping("/article/update")
    public String update(Article article, ArticleContent articleContent, HttpServletRequest request, Model model) {
        article.setIpAddress(request.getRemoteAddr());

        //회원정보 관련된 set은 세션을 구현한 후에 넣어주어야 함
        article.setNickName("관리자");

        articleService.updateArticle(article, articleContent);

        model.addAttribute("id", article.getId());
        return "redirect:/article/read";
    }

    //게시판 글 삭제
    @GetMapping("/article/delete")
    public String delete(@RequestParam("id") Long id,@RequestParam("categoryid")int categoryId, Model model) {
        articleService.deleteArticle(id);
        model.addAttribute("categoryid", categoryId);
        return "redirect:/article/list";
    }
}
