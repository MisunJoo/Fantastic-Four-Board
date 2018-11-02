package project.ffboard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberPermission {
    private Long memberId;
    private String permName;
}
