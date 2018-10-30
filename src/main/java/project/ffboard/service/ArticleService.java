package project.ffboard.service;

import project.ffboard.dto.Article;
import project.ffboard.dto.ArticleContent;

import java.util.List;

public interface ArticleService {
    public int addArticle(Article article);
    public int addArticleContent(ArticleContent articleContent);
    public int updateCount(Article article);
    public int deleteArticle(Article article);
    public int updateArticle(Article article);
    public int updateArticleContent(ArticleContent articleContent);
    public int getArticle(Article article);
    public int getArticleContent(ArticleContent articleContent);
    public List<Article> getArticleList(int categoryId);
    public List<Article> getArticleList(int categoryId, String searchType, String searchWord);
}
