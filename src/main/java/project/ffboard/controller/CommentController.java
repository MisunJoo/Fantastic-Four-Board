package project.ffboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.ffboard.dto.Comment;
import project.ffboard.dto.CommentCounting;
import project.ffboard.service.CommentService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping("/comment/list")
    public String getList(ModelMap modelMap, @RequestParam(value="modification", defaultValue = "false") String modification,
                            @RequestParam(value = "commentId",defaultValue = "") Long commentId,
                            @RequestParam(value = "articleId", defaultValue = "1")String articleId,
                            @RequestParam(value = "addChild", defaultValue = "false")String addChild,
                            @RequestParam(value = "page", defaultValue = "1")String page,
                            @RequestParam(value = "posts", defaultValue = "5")String posts,
                            @RequestParam(value = "totalPage", defaultValue = "1")String totalPage){

        modelMap.addAttribute("comments",
                commentService.getCommentList(Long.parseLong(articleId), Integer.parseInt(page), Integer.parseInt(posts)));

        if(modification.equals("true")) {
            modelMap.addAttribute("modification", modification);
        }
        if(addChild.equals("true")) {
            modelMap.addAttribute("addChild", addChild);
        }
        modelMap.addAttribute("commentId", commentId);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("posts", posts);
        modelMap.addAttribute("totalPage", commentService.getCount(Long.parseLong(articleId),Integer.parseInt(totalPage),Integer.parseInt(posts)));

        return "comment";
    }

    @GetMapping("/comment/write")
    public String writeChild(@RequestParam("id")Long id,
                             @RequestParam("articleid")Long articleId){
        return "redirect:/article/read?id="+articleId+"&commentId="+id+"&addChild=true";
    }


    @PostMapping("/comment/write")
    public String write(@ModelAttribute Comment comment, @ModelAttribute CommentCounting commentCounting) {
        if(comment.getGroupId()==null){      // 그냥 댓글
            comment.setGroupSeq(0);
            comment.setDepthLevel(0);
        }else{                               // 답글일 때
            if(comment.getDepthLevel() < 2) comment.setDepthLevel(comment.getDepthLevel()+1);
            comment.setGroupSeq(comment.getGroupSeq()+1);
        }

        // 나중에 수정
        comment.setIpAddress("124.2223");
        comment.setMemberId(1L);
        comment.setNickName("nick");
        commentService.addComment(comment);
        return "redirect:/article/read?id="+comment.getArticleId();

    }

    @GetMapping("/comment/delete")
    public String delete(@RequestParam("id") Long id, @RequestParam("articleid")Long articleId){
        commentService.deleteComment(id);
        return "redirect:/article/read?id="+articleId;
    }

    @PostMapping("/comment/modify")
        public String modify(@ModelAttribute Comment comment){
            commentService.modifyComment(comment);
            return "redirect:/article/read?id="+comment.getArticleId();
    }

    @GetMapping("/comment/modifyform")
    public String modifyForm(@RequestParam("id")Long commentId,
                             @RequestParam("articleid") Long articleId ){
        return "redirect:/article/read?id="+articleId+"&modification=true&commentId="+commentId;
    }
}
