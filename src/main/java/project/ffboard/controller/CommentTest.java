package project.ffboard.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import project.ffboard.config.ApplicationConfig;
import project.ffboard.dao.CommentDao;
import project.ffboard.dto.Comment;
import project.ffboard.service.CommentService;
import project.ffboard.service.CommentServiceImpl;

import javax.sql.DataSource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommentTest {
    private static CommentService commentService;

    public CommentTest(CommentService commentService) {
        System.out.println("commenttst");
        this.commentService = commentService;
    }



    public static void main(String args[]){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        DataSource ds = ac.getBean(DataSource.class);
        CommentDao commentDao = new CommentDao(ds);
        CommentService commentService = new CommentServiceImpl(commentDao);
        Comment comment = new Comment();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        comment.setArticleId(1L);
        comment.setNickName("nick");
        comment.setContent("hello");
        comment.setGroupId(1L);
        comment.setDepthLevel(1);
        comment.setGroupSeq(11);
        comment.setRegdate(format.format(new Date()));
        comment.setUpddate(format.format(new Date()));
        comment.setIpAddress("124.2223");
        comment.setMemberId(0L);
        comment.setIsDeleted(false);
        System.out.println(commentService.addComment(comment));

        commentDao.deleteComment(2L);

        comment.setId(4L);
        comment.setContent("bye");
        commentService.updateComment(comment);

        List<Comment> comments = commentDao.getCommentList(1L);
        for(Comment cm : comments){
            System.out.println(cm.getContent());
        }

    }
}
