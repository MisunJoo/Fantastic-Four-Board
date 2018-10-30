package project.ffboard.service;

import org.springframework.stereotype.Service;
import project.ffboard.dao.ArticleDao;
import project.ffboard.dto.Article;
import project.ffboard.dto.ArticleContent;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    private ArticleDao articleDao;

    public ArticleServiceImpl(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    public int addArticle(Article article) {
        return 0;
    }

    @Override
    public int addArticleContent(ArticleContent articleContent) {
        return 0;
    }

    @Override
    public int updateCount(Article article) {
        return 0;
    }

    @Override
    public int deleteArticle(Article article) {
        return 0;
    }

    @Override
    public int updateArticle(Article article) {
        return 0;
    }

    @Override
    public int updateArticleContent(ArticleContent articleContent) {
        return 0;
    }

    @Override
    public int getArticle(Article article) {
        return 0;
    }

    @Override
    public int getArticleContent(ArticleContent articleContent) {
        return 0;
    }

    @Override
    public List<Article> getArticleList(int categoryId) {
        return null;
    }

    @Override
    public List<Article> getArticleList(int categoryId, String searchType, String searchWord) {
        return null;
    }
}
