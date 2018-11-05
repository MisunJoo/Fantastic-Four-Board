package project.ffboard.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class Member {
    private Long id;
    private String nickName;
    private String password;
    private String email;
    private Set<String> perms;
}
