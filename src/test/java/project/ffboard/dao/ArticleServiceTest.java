package project.ffboard.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import project.ffboard.config.ApplicationConfig;
import project.ffboard.service.ArticleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

//    @Bean
//    public ArticleServiceTest(ArticleService articleService) {
//        this.articleService = articleService;
//    }

    @Test
    public void addArticle() {

    }
}
