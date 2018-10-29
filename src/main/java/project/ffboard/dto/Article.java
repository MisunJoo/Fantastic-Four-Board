package project.ffboard.dto;

import java.util.Date;

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
    // private String content;

    public Article() {
    }

    public Article(String title, String nickName, Long groupId, int depthLevel, int groupSeq, Date regdate, int categoryId, String ipAddress, Long memberId) {
        this.title = title;
        this.nickName = nickName;
        this.groupId = groupId;
        this.depthLevel = depthLevel;
        this.groupSeq = groupSeq;
        this.regdate = regdate;
        this.categoryId = categoryId;
        this.ipAddress = ipAddress;
        this.memberId = memberId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public Date getUpddate() {
        return upddate;
    }

    public void setUpddate(Date upddate) {
        this.upddate = upddate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }
}

