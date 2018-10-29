package project.ffboard.dto;

public class ArticleContent {
    private Long article_id;
    private String content;

    public ArticleContent(Long article_id, String content) {
        this.article_id = article_id;
        this.content = content;
    }

    public Long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
