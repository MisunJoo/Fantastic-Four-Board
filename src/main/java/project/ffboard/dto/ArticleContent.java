package project.ffboard.dto;

public class ArticleContent {
    private Long articleId;
    private String content;

    public ArticleContent() {
    }

    public ArticleContent(Long article_id, String content) {
        this.articleId = article_id;
        this.content = content;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
