package project.ffboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Member {
    private Long id;
    private String nickName;
    private String password;
    private String email;
    private Map<String, String> permMap;
}
