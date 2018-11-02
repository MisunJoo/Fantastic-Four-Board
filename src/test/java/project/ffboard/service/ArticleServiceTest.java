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

import java.util.Date;
import java.util.List;

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
        // Assert.assertEquals((Object)20L, articleService.addArticle(article));
    }

//    @Test
//    public void addArticleContent() {
//        ArticleContent articleContent = new ArticleContent();
//
//        articleContent.setArticleId(10L);
//        articleContent.setContent("hihi");
//
//        //insert 된 건수가 리턴 (1이여야함)
//        Assert.assertEquals(1,articleService.addArticleContent(articleContent));
//    }

    @Test
    public void updateCount() {
        Long articleId = 20L;

        //update된 건수를 리턴 (1이어야함)
        Assert.assertEquals(1,articleService.updateCount(articleId));
    }

    @Test
    public void deleteArticle() {
        Long articleId=12L;

        //삭제된(isDeleted가 1이된) 건수를 리턴 (1이어야함)
        Assert.assertEquals(1,articleService.deleteArticle(articleId));
    }

//    @Test
//    public void updateArticle() {
//        Article article = new Article();
//        article.setTitle("수정을 한 제목");
//        article.setIpAddress("168.1.123.1");
//        article.setUpddate(new Date());
//        article.setNickName("푸르");
//        article.setId(8L);
//        //수정된 건수를 리턴 (1이어야함)
//        Assert.assertEquals(1, articleService.updateArticle(article));
//    }
//
//    @Test
//    public void updateArticleContent() {
//        ArticleContent articleContent = new ArticleContent();
//        articleContent.setArticleId(8L);
//        articleContent.setContent("안녕안녕~~~");
//        //수정된 건수를 리턴 (1이어야함)
//        Assert.assertEquals(1, articleService.updateArticleContent(articleContent));
//    }

    @Test
    public void getArticle() {
        Long id = 20L;

        Article article = articleService.getArticle(id);
        //가져온 id
        Assert.assertEquals(article.getId(),(Object)20L);

        System.out.println(article.getTitle()+" "+article.getNickName());
    }

    @Test
    public void getArticleContent() {
        Long id = 20L;

        ArticleContent articleContent = articleService.getArticleContent(id);

        //가져온 articleId
        Assert.assertEquals(articleContent.getArticleId(),(Object)20L);

        System.out.println(articleContent.getContent()+" "+articleContent.getArticleId());
    }

    @Test
    public void getArticleList() {
        int categoryId = 1;
        int start =0;

        List<Article> articleList =  articleService.getArticleList(categoryId, start);

        //가져온 article의 사이즈
        Assert.assertEquals(5,articleList.size());

        for (Article article : articleList) {
            System.out.println(article.getTitle()+" " +article.getNickName()+" "+article.getId());
        }
    }

    @Test
    public void getArticleListSearch() {
        int categoryId = 1;
        int start =0;
        String searchType = "제목";
        String searchWord = "자바";

        List<Article> articleList =  articleService.getArticleList(categoryId, start, searchType, searchWord);

        //가져온 article의 사이즈
//        Assert.assertEquals(5,articleList.size());

        for (Article article : articleList) {
            System.out.println(article.getTitle()+" " +article.getNickName()+" "+article.getId());
        }
    }
}

