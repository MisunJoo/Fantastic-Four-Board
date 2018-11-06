package project.ffboard.service;

import org.springframework.web.multipart.MultipartFile;
import project.ffboard.dto.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

//TODO : 인터페이스에 대한 철학을 정해야 할 것 같다.
public interface ArticleService {
    public int addArticle(Article article, ArticleContent articleContent, MultipartFile file, ArticleCounting articleCounting);
    public int updateCount(Long id);
    public int deleteArticle(Long id);
    public int updateArticle(Article article,ArticleContent articleContent, MultipartFile file);
    public Article getArticle(Long id);
    public ArticleContent getArticleContent(Long id);
    public List<Article> getArticleList(int categoryId, int page, int posts);
    public List<Article> getArticleList(int categoryId, int start, String searchType, String searchWord);
    public List<Category> getCategoryList();
    public int getCount(int categoryId, int totalPage, int posts);
    public void downloadFile(HttpServletResponse response, Long articleId);
    public ArticleFile isExistFile(Long articleId);
}
