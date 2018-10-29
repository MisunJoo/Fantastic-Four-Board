package project.ffboard.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import project.ffboard.config.ApplicationConfig;
import project.ffboard.dao.CommentDao;
import project.ffboard.dto.Comment;

import javax.sql.DataSource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommentTest {
    public static void main(String args[]){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        DataSource ds = ac.getBean(DataSource.class);
        CommentDao commentDao = new CommentDao(ds);
        Comment comment = new Comment();


        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        comment.setContent("aaaaa");
        comment.setNickName("nick");
        comment.setDepthLevel(1);
        comment.setGroupId(1L);
        comment.setGroupSeq(11);
        comment.setIpAddress("124.2223");
        comment.setIsDeleted(false);
        comment.setRegdate(format.format(new Date()));
        comment.setUpdate(format.format(new Date()));
        comment.setArticleId(1L);
        comment.setMemberId(0L);
        System.out.println(commentDao.addComment(comment));
    }
}
