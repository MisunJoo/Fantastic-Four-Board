package project.ffboard.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.ffboard.dao.ArticleDao;
import project.ffboard.dto.Article;
import project.ffboard.dto.ArticleContent;

import java.util.List;
@Service
public class ArticleServiceImpl implements ArticleService {
    private int limit = 5;
    private ArticleDao articleDao;

    public ArticleServiceImpl(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    @Transactional
    public int addArticle(Article article, ArticleContent articleContent) {
        //지금 쓰는 글이 답글인가
        if (article.getGroupId() != null && article.getDepthLevel() > 0 && article.getGroupSeq() > 0) {
            if(article.getDepthLevel() < 2) article.setDepthLevel(article.getDepthLevel()+1);
            article.setGroupSeq(article.getGroupSeq()+1);
        }

        articleContent.setArticleId(articleDao.addArticle(article));
        return articleDao.addArticleContent(articleContent);
    }


    @Override
    @Transactional
    public int updateCount(Long id) {
        return articleDao.updateCount(id);
    }

    @Override
    @Transactional
    public int deleteArticle(Long id) {
        return articleDao.deleteArticle(id);
    }

    @Override
    @Transactional
    public int updateArticle(Article article, ArticleContent articleContent) {
        articleContent.setArticleId(articleDao.updateArticle(article));
        return articleDao.updateArticleContent(articleContent);
    }

    @Override
    @Transactional
    public Article getArticle(Long id) {
        return articleDao.getArticle(id);
    }

    @Override
    @Transactional
    public ArticleContent getArticleContent(Long id) {
        return articleDao.getArticleContent(id);
    }

    @Override
    @Transactional
    public List<Article> getArticleList(int categoryId, int start) {
        return articleDao.getArticleList(categoryId,start,limit);
    }

    @Override
    @Transactional
    public List<Article> getArticleList(int categoryId, int start, String searchType, String searchWord) {
        return articleDao.getArticleList(categoryId,start,limit,searchType,searchWord);
    }
}
