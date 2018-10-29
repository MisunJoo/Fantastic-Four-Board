package project.ffboard.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import project.ffboard.config.ApplicationConfig;
import project.ffboard.dto.Article;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ArticleDaoTest {

    @Autowired
    private ArticleDao articleDao;

    @Test
    public void init(){
        System.out.println("init");
    }

   @Test
    public void addArticle() throws Exception {
        Article article = new Article();
        article.setCategoryId(1);
        article.setDepthLevel(0);
        article.setGroupSeq(0);
        article.setIpAddress("192.168.0.1");
        article.setMemberId(1L);
        article.setNickName("유어스토리");
        article.setTitle("333자바게시판인가요?");
        article.setHit(0);
        article.setRegdate(new Date());
        article.setIsDeleted(false);
//        Assert.assertEquals(java.util.Optional.ofNullable(test), java.util.Optional.ofNullable(1L));
        Assert.assertEquals((Object)1,(Object)articleDao.addArticle(article));
    }

    @Test
    public void getArticle() throws Exception {
        Assert.assertEquals(articleDao.getArticle(1L).getCategoryId(),1);
    }
}
