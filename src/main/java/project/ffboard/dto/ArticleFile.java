package project.ffboard.dto;

public class ArticleFile {
    private Long articleId;
    private String originName;
    private String storedName;
    private Long size;
    private String path;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getStoredName() {
        return storedName;
    }

    public void setStoredName(String storedName) {
        this.storedName = storedName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ArticleFile{" +
                "articleId=" + articleId +
                ", originName='" + originName + '\'' +
                ", storedName='" + storedName + '\'' +
                ", size=" + size +
                ", path='" + path + '\'' +
                '}';
    }
}
