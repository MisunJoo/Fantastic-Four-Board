package project.ffboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Article {
    private Long id;
    private String title;
    private String nickName;
    private Long groupId;
    private int depthLevel;
    private int groupSeq;
    private Date regdate;
    private Date upddate;
    private int categoryId;
    private String ipAddress;
    private Long memberId;
    private Boolean isDeleted;
    private int hit;
}

