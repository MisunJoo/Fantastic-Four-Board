package project.ffboard.service;

import project.ffboard.dto.Member;

public interface MemberService {
    public Long signUp(Member member);
    public Member login(Member member);
    public int logout(Member member);
}
