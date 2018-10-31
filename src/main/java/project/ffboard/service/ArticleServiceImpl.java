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
    public Long addArticle(Article article) {
        //지금 쓰는 글이 답글인가
        if (article.getGroupId() != null && article.getDepthLevel() > 0 && article.getGroupSeq() > 0) {
            if(article.getDepthLevel() < 2) article.setDepthLevel(article.getDepthLevel()+1);
            article.setGroupSeq(article.getGroupSeq()+1);
        }

        return articleDao.addArticle(article);
    }

    public int addArticleContent(ArticleContent articleContent) {
        return articleDao.addArticleContent(articleContent);
    }


    @Override
    public int updateCount(Long id) {
        return articleDao.updateCount(id);
    }

    @Override
    public int deleteArticle(Long id) {
        return articleDao.deleteArticle(id);
    }

    @Override
    public int updateArticle(Article article) {
        return articleDao.updateArticle(article);
    }

    @Override
    public int updateArticleContent(ArticleContent articleContent) {
        return articleDao.updateArticleContent(articleContent);
    }

    @Override
    public Article getArticle(Long id) {
        return articleDao.getArticle(id);
    }

    @Override
    public ArticleContent getArticleContent(Long id) {
        return null;
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