package project.ffboard.service;

import project.ffboard.dto.Article;
import project.ffboard.dto.ArticleContent;

import java.util.List;

public interface ArticleService {
    public Long addArticle(Article article);
    public int addArticleContent(ArticleContent articleContent);
    public int updateCount(Long id);
    public int deleteArticle(Long id);
    public int updateArticle(Article article);
    public int updateArticleContent(ArticleContent articleContent);
    public int getArticle(Article article);
    public int getArticleContent(ArticleContent articleContent);
    public List<Article> getArticleList(int categoryId);
    public List<Article> getArticleList(int categoryId, String searchType, String searchWord);
}
