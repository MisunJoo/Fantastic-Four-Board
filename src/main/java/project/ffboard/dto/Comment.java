package project.ffboard.dto;

import java.util.Date;

public class Comment {
    private Long id;
    private Long articleId;
    private String nickName;
    private String content;
    private Long groupId;
    private int depthLevel;
    private int groupSeq;
    private Date regdate;
    private Date update;
    private String ipAddress;
    private Long memberId;
    private Boolean isDeleted;

    public Comment() {
    }

    public Comment(Long articleId, String nickName, String content, Long groupId, int depthLevel, int groupSeq, Date regdate, String ipAddress, Long memberId) {
        this.articleId = articleId;
        this.nickName = nickName;
        this.content = content;
        this.groupId = groupId;
        this.depthLevel = depthLevel;
        this.groupSeq = groupSeq;
        this.regdate = regdate;
        this.ipAddress = ipAddress;
        this.memberId = memberId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public int getDepthLevel() {
        return depthLevel;
    }

    public void setDepthLevel(int depthLevel) {
        this.depthLevel = depthLevel;
    }

    public int getGroupSeq() {
        return groupSeq;
    }

    public void setGroupSeq(int groupSeq) {
        this.groupSeq = groupSeq;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
