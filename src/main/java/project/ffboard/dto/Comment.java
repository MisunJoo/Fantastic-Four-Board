package project.ffboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Comment {
    private Long id;
    private Long articleId;
    private String nickName;
    private String content;
    private Long groupId;
    private int depthLevel;
    private int groupSeq;
    private Date regdate;
    private Date upddate;
    private String ipAddress;
    private Long memberId;
    private Boolean isDeleted;
    private Long counting;
}
