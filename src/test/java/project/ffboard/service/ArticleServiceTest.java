package project.ffboard.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import project.ffboard.config.ApplicationConfig;
import project.ffboard.dto.Article;
import project.ffboard.dto.ArticleContent;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@Component
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    public void init() {
        System.out.println("init()");
    }

    @Test
    public void addArticle() {
        Article article = new Article();
        ArticleContent articleContent = new ArticleContent();

        article.setCategoryId(1);
        article.setDepthLevel(0);
        article.setGroupSeq(0);
        article.setIpAddress("192.168.0.1");
        article.setMemberId(1L);
        article.setNickName("유어스토리");
        article.setTitle("굿모닝");
        article.setHit(0);

        //article의 PK가 리턴
        Assert.assertEquals((Object)20L, articleService.addArticle(article));
    }

    @Test
    public void addArticleContent() {
        ArticleContent articleContent = new ArticleContent();

        articleContent.setArticleId(20L);
        articleContent.setContent("hihi");

        //insert 된 건수가 리턴 (1이여야함)
        Assert.assertEquals(1,articleService.addArticleContent(articleContent));
    }
}
