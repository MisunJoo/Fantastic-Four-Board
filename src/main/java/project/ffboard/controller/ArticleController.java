package project.ffboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.ffboard.dto.Article;
import project.ffboard.dto.ArticleContent;
import project.ffboard.dto.ArticleFile;
import project.ffboard.service.ArticleService;
import project.ffboard.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ArticleController {
    private ArticleService articleService;
    private CommentService commentService;

    public ArticleController(ArticleService articleService, CommentService commentService) {
        this.articleService = articleService;
        this.commentService = commentService;
    }

    //게시판 글 목록 가져오기
    @GetMapping("/article/list")
    public String list(@RequestParam("categoryid")int categoryId, @RequestParam(value = "start", defaultValue = "0")int start, Model model) {
        getCategoryList(model); //게시판 네비게이션 목록을 위한 카테고리 목록 가져오기
        model.addAttribute("articleList", articleService.getArticleList(categoryId,start));
        model.addAttribute("categoryId", categoryId);
        return "/article/list";
    }

    //게시판 검색된 글 목록 가져오기
    @PostMapping("/article/search")
    public String list(@RequestParam("categoryId")int categoryId, @RequestParam(value = "start", defaultValue = "0")int start,
                       @RequestParam("searchType") String searchType, @RequestParam("searchWord") String searchWord, Model model) {
        getCategoryList(model); //게시판 네비게이션 목록을 위한 카테고리 목록 가져오기
        model.addAttribute("articleList", articleService.getArticleList(categoryId,start,searchType,searchWord));
        model.addAttribute("categoryId", categoryId);
        return "/article/list";
    }

    //게시판 글 읽기
    @GetMapping("/article/read")
    public String read(@RequestParam("id")Long id,
                       @RequestParam(value="modification", defaultValue = "false") String modification,
                       @RequestParam(value = "commentId", defaultValue = "") Long commentId,
                       @RequestParam(value="addChild", defaultValue = "false")String addChild,
                       Model model){
        getCategoryList(model); //게시판 네비게이션 목록을 위한 카테고리 목록 가져오기

        Article article = articleService.getArticle(id);

        //만약 삭제된 글에 비정상적으로 접근할 시에 바로 목록으로 리다이렉트
        if (article.getIsDeleted() == true) {
            return "redirect:/article/list?categoryid="+article.getCategoryId();
        }

        //만약 파일이 존재한다면, 파일관련 정보도 추가
        ArticleFile file = articleService.isExistFile(id);
        if (file!=null) {
            model.addAttribute("fileInfo", file);
        }

        model.addAttribute("article", article);
        model.addAttribute("articleContent", articleService.getArticleContent(id));

        //댓글 삽입코드 시작
        model.addAttribute("comments", commentService.getCommentList(id));
        if(modification.equals("true")) {
            model.addAttribute("modification", modification);
        }
        if(addChild.equals("true")) {
            model.addAttribute("addChild", addChild);
        }
        model.addAttribute("commentId", commentId);
        //댓글 삽입 끝

        return "/article/read";
    }

    //download
    @GetMapping("/article/download/{id}")
    @ResponseBody
    public void download(@PathVariable("id") Long id,
                         HttpServletResponse response) {
        articleService.downloadFile(response,id);
    }

    //게시판 글 쓰기
    @GetMapping("/article/write")
    public String write(@RequestParam("categoryid") int categoryId, Model model) {
        getCategoryList(model); //게시판 네비게이션 목록을 위한 카테고리 목록 가져오기
        model.addAttribute("categoryId", categoryId);
        return "/article/write";
    }

    @PostMapping("/article/write")
    public String write(Article article, ArticleContent articleContent,
                        @RequestParam("file") MultipartFile file,
                        HttpServletRequest request, Model model) {
        article.setGroupSeq(0);
        article.setDepthLevel(0);
        article.setIpAddress(request.getRemoteAddr());

        //회원정보 관련된 set은 세션을 구현한 후에 넣어주어야 함
        article.setMemberId(1L);
        article.setNickName("관리자");

        articleService.addArticle(article,articleContent,file);

        return "redirect:/article/list?categoryid="+article.getCategoryId();
    }

    //게시판 답글달기
    @GetMapping("/article/reply")
    public String reply(@RequestParam("id")Long id,Model model) {
        getCategoryList(model); //게시판 네비게이션 목록을 위한 카테고리 목록 가져오기
        model.addAttribute("parentId", id);
        return "/article/reply";
    }

    @PostMapping("/article/reply")
    public String reply(Article article, ArticleContent articleContent,
                        @RequestParam("file") MultipartFile file,
                        @RequestParam("parentId")Long parentId, HttpServletRequest request,
                        Model model) {
        //계층형을 위한 처리, 부모의 groupId를받고, groupseq와 depthlevel을 부모보다 1크게한다.
        Article parentArticle = articleService.getArticle(parentId);
        article.setGroupId(parentArticle.getGroupId());
        article.setGroupSeq(parentArticle.getGroupSeq());
        article.setDepthLevel(parentArticle.getDepthLevel());

        article.setCategoryId(parentArticle.getCategoryId());
        article.setIpAddress(request.getRemoteAddr());

        //회원정보 관련된 set은 세션을 구현한 후에 넣어주어야 함
        article.setMemberId(1L);
        article.setNickName("관리자");

        articleService.addArticle(article,articleContent,file);
        return "redirect:/article/list?categoryid="+article.getCategoryId();
    }

    //게시판 글 수정
    @GetMapping("/article/update")
    public String update(@RequestParam("id")Long id, Model model) {
        getCategoryList(model); //게시판 네비게이션 목록을 위한 카테고리 목록 가져오기
        model.addAttribute("article",articleService.getArticle(id));
        model.addAttribute("articleContent", articleService.getArticleContent(id));
        return "/article/update";
    }

    @PostMapping("/article/update")
    public String update(Article article, ArticleContent articleContent,
                         @RequestParam("file") MultipartFile file,
                         HttpServletRequest request, Model model) {
        article.setIpAddress(request.getRemoteAddr());

        //회원정보 관련된 set은 세션을 구현한 후에 넣어주어야 함
        article.setNickName("관리자");

        articleService.updateArticle(article, articleContent, file);

        return "redirect:/article/read?id="+article.getId();
    }

    //게시판 글 삭제
    @GetMapping("/article/delete")
    public String delete(@RequestParam("id") Long id,@RequestParam("categoryid")int categoryId, Model model) {
        articleService.deleteArticle(id);
        return "redirect:/article/list?categoryid="+categoryId;
    }

    //게시판 네비게이션 카테고리 목록 자겨오기
    public void getCategoryList(Model model) {
        model.addAttribute("categoryList", articleService.getCategoryList());
    }
}
