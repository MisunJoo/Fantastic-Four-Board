package project.ffboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.ffboard.dao.MemberDao;
import project.ffboard.dto.Member;
import project.ffboard.exception.FFException;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberDao memberDao;
    @Autowired
    @Qualifier(value = "DaoException")
    private FFException daoException;


    public MemberServiceImpl(MemberDao memberDao, FFException daoException) {
        this.daoException=daoException;
        this.memberDao = memberDao;
    }

    @Override
    @Transactional
    public Long signUp(Member member) {
        Long result = null;
        try{
           result= memberDao.signUp(member);
        }catch (DataAccessException dae){
            daoException.printLog(dae.toString());
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public int login(Member member) {
        return 0;
    }

    @Override
    @Transactional
    public int logout(Member member) {
        return 0;
    }
}
