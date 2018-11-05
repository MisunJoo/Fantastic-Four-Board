package project.ffboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.ffboard.dao.AdminDao;
import project.ffboard.dao.MemberDao;
import project.ffboard.dto.Member;
import project.ffboard.exception.FFException;

import java.util.HashSet;
import java.util.Set;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberDao memberDao;
    private AdminDao adminDao;
    @Autowired
    @Qualifier(value = "DaoException")
    private FFException daoException;


    public MemberServiceImpl(MemberDao memberDao, FFException daoException, AdminDao adminDao) {
        this.daoException = daoException;
        this.memberDao = memberDao;
        this.adminDao = adminDao;
    }

    @Override
    @Transactional
    public Long signUp(Member member) {
        Long result = null;
        try {
            result = memberDao.signUp(member);
        } catch (DataAccessException dae) {
            daoException.printLog(dae.toString());
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Member login(Member member) {
        try {
            member = memberDao.login(member);
            // 맵핑테이블에서 권한 받기
            Set set = new HashSet();
            for(String perm: adminDao.getMemPerm(member.getId())){
                set.add(perm);
            }
            member.setPerms(set);
            return member;
        } catch (DataAccessException dae) {
            daoException.printLog(dae.toString());
            return null;
        }
    }

    @Override
    @Transactional
    public int logout(Member member) {
        return 0;
    }
}
