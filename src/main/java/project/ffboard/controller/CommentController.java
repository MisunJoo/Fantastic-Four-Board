package project.ffboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.ffboard.dto.Comment;
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

    @GetMapping("/comment/writeform")
    public String writeForm(ModelMap modelMap){
        modelMap.addAttribute("comments", commentService.getCommentList(2L));
        return "comment";
    }

    @PostMapping("/comment/write")
    public String write(@ModelAttribute Comment comment){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("gid="+comment.getGroupId());
        if(comment.getId()==null){      // 그냥 댓글
            comment.setGroupSeq(0);
            comment.setDepthLevel(0);
        }else{                          // 답글
            comment.setGroupId(comment.getId());
            comment.setDepthLevel(comment.getDepthLevel()+1);
        }

        // 나중에 수정
        comment.setRegdate(format.format(new Date()));
        comment.setIpAddress("124.2223");
        comment.setMemberId(0L);
        comment.setArticleId(2L);
        comment.setNickName("nick");

        commentService.addComment(comment);
        return "redirect:/comment/writeform";
    }

    @GetMapping("/comment/delete")
    public String commentDelete(Long id){
        commentService.deleteComment(id);

        return "redirect:/comment/writeform";
    }

}
