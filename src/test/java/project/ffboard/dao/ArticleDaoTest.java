package project.ffboard.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import project.ffboard.config.ApplicationConfig;
import project.ffboard.dto.Article;
import project.ffboard.dto.ArticleContent;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ArticleDaoTest {
//
//    @Autowired
//    private ArticleDao articleDao;
//
//    @Test
//    public void init(){
//        System.out.println("init");
//    }
//
//   @Test
//    public void addArticle() throws Exception {
//        Article article = new Article();
//        article.setCategoryId(1);
//        article.setDepthLevel(0);
//        article.setGroupSeq(0);
//        article.setIpAddress("192.168.0.1");
//        article.setMemberId(1L);
//        article.setNickName("유어스토리");
//        article.setTitle("444밥먹고합시다1231234");
//        article.setHit(0);
////        article.setRegdate(new Date());
//        article.setIsDeleted(false);
////        Assert.assertEquals(java.util.Optional.ofNullable(test), java.util.Optional.ofNullable(1L));
//        Assert.assertEquals((Object)1,(Object)articleDao.addArticle(article));
//    }
//
//    @Test
//    public void addArticleContent() throws Exception {
//        ArticleContent articleContent = new ArticleContent(9L, "사실 정시윤입니다.");
//
//        Assert.assertEquals(articleDao.addArticleContent(articleContent), 1);
//    }
//
//    @Test
//    public void addArticleReply() throws Exception {
//        Article article = new Article();
//        article.setCategoryId(1);
//        article.setDepthLevel(1+1);
//        article.setGroupSeq(1+1);
//        article.setIpAddress("192.168.0.1");
//        article.setMemberId(1L);
//        article.setNickName("유어스토리");
//        article.setTitle("짜?");
//        article.setHit(0);
//        article.setGroupId(3L);
////        Assert.assertEquals(java.util.Optional.ofNullable(test), java.util.Optional.ofNullable(1L));
//        Assert.assertEquals((Object)1,(Object)articleDao.addArticle(article));
//    }
//
//    @Test
//    public void updateCount() {
//        Assert.assertEquals(articleDao.updateCount(9L), 1);
//    }
//
//    @Test
//    public void deleteArticle() {
//        Assert.assertEquals(articleDao.deleteArticle(1L), 1);
//    }
//
////    @Test
////    public void updateArticle() {
////        Article article = new Article();
////        article.setId(9L);
////        article.setTitle("프로그래밍자바로시작하면어떨까요?");
////        article.setUpddate(new Date());
////        Assert.assertEquals(articleDao.updateArticle(article), 1);
////    }
//
//    @Test
//    public void updateArticleContent() {
//        ArticleContent articleContent = new ArticleContent(1L, "수정된 내용");
//        Assert.assertEquals(articleDao.updateArticleContent(articleContent), 1);
//    }
//
//    @Test
//    public void getArticle() throws Exception {
//        Assert.assertEquals((Object)articleDao.getArticle(1L).getId(), (Object)1L);
//    }
//
//    @Test
//    public void getArticleContent() {
//        Assert.assertEquals(articleDao.getArticleContent(1L).getArticleId(), (Object)1L);
//    }
//
//    @Test
//    public void getArticleList() {
//        List<Article> articleList = articleDao.getArticleList(1, 0, 5);
//        Assert.assertEquals(articleList.size(),5);
//        for (Article article : articleList) {
//            System.out.println(article.getTitle());
//        }
//    }
//
//    /*검색용*/
//    //제목
//    @Test
//    public void getArticleListSearchTitle() {
//        List<Article> articleList = articleDao.getArticleList(1, 0, 5,"제목","자바");
//        //Assert.assertEquals(articleList.size(),5);
//
//        for (Article article : articleList) {
//            System.out.println(article.getTitle());
//        }
//    }
//    //내용
//    @Test
//    public void getArticleListSearchContent() {
//        List<Article> articleList = articleDao.getArticleList(1, 0, 5,"내용","수정된");
//        //Assert.assertEquals(articleList.size(),5);
//
//        for (Article article : articleList) {
//            System.out.println(article.getTitle());
//        }
//    }
//
//    //이름
//    @Test
//    public void getArticleListSearchNickName() {
//        List<Article> articleList = articleDao.getArticleList(1, 0, 5,"이름","어스");
//        //Assert.assertEquals(articleList.size(),5);
//
//        for (Article article : articleList) {
//            System.out.println(article.getTitle() + article.getNickName());
//        }
//    }
//
//    //제목+내용
//    @Test
//    public void getArticleListSearchTitleNContent() {
//        List<Article> articleList = articleDao.getArticleList(1, 0, 5,"제목+내용","정시윤");
//        //Assert.assertEquals(articleList.size(),5);
//
//        for (Article article : articleList) {
//            System.out.println(article.getTitle() + article.getNickName());
//        }
//    }

}
