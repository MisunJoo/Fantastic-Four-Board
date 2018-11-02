package project.ffboard.service;

import org.springframework.stereotype.Service;
import project.ffboard.dao.MemberDao;
import project.ffboard.dto.Member;

@Service
public class MemberServiceImpl implements MemberService {
    MemberDao memberDao;

    public MemberServiceImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public Long signUp(Member member) {
        System.out.println(member.getEmail());
        return memberDao.signUp(member);
    }

    @Override
    public int login(Member member) {
        return 0;
    }

    @Override
    public int logout(Member member) {
        return 0;
    }
}
