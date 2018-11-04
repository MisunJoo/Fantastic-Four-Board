package project.ffboard.service;

import org.springframework.web.multipart.MultipartFile;
import project.ffboard.dto.Article;
import project.ffboard.dto.ArticleContent;
import project.ffboard.dto.Category;

import java.util.List;

//TODO : 인터페이스에 대한 철학을 정해야 할 것 같다.
public interface ArticleService {
    public int addArticle(Article article, ArticleContent articleContent, MultipartFile file);
    public int updateCount(Long id);
    public int deleteArticle(Long id);
    public int updateArticle(Article article,ArticleContent articleContent, MultipartFile file);
    public Article getArticle(Long id);
    public ArticleContent getArticleContent(Long id);
    public List<Article> getArticleList(int categoryId, int start);
    public List<Article> getArticleList(int categoryId, int start, String searchType, String searchWord);
    public List<Category> getCategoryList();
}
