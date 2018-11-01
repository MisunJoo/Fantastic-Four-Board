package project.ffboard.service;

import project.ffboard.dto.Article;
import project.ffboard.dto.ArticleContent;

import java.util.List;

public interface ArticleService {
    public int addArticle(Article article,ArticleContent articleContent);
    public int updateCount(Long id);
    public int deleteArticle(Long id);
    public int updateArticle(Article article);
    public int updateArticleContent(ArticleContent articleContent);
    public Article getArticle(Long id);
    public ArticleContent getArticleContent(Long id);
    public List<Article> getArticleList(int categoryId, int start);
    public List<Article> getArticleList(int categoryId, int start, String searchType, String searchWord);
}
