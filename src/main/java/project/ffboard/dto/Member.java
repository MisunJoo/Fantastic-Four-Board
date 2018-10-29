package project.ffboard.dto;

import java.util.Map;

public class Member {
    private Long id;
    private String nickName;
    private String password;
    private String email;
    private Map<String, String> permMap;

    public Member() {
    }

    public Member(String nickName, String password, String email) {
        this.nickName = nickName;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
