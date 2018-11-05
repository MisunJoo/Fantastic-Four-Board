package project.ffboard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleFile {
    private Long articleId;
    private String originName;
    private String storedName;
    private String contentType;
    private Long size;
    private String path;
}
