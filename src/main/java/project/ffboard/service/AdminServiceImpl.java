package project.ffboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.ffboard.dao.AdminDao;
import project.ffboard.dto.Member;
import project.ffboard.exception.FFException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AdminServiceImpl implements AdminService{
    private AdminDao adminDao;
    @Autowired
    @Qualifier(value = "DaoException")
    private FFException daoException;

    public AdminServiceImpl(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    @Transactional(readOnly=true)
    public List<Member> getMembers(String pg, String email, int limit) {
        List<Member> result = null;

        try {
            result = adminDao.getMembers(Integer.parseInt(pg), email, limit);
            // member_permission에서 권한들을 읽어와서 setPerSet 해주기.
            for(Member m : result){
                Set set = new HashSet();
               for(String perm: adminDao.getMemPerm(m.getId())){
                   set.add(perm);
               }
               m.setPerms(set);
            }
        } catch (DataAccessException dae) {
            daoException.printLog(dae.toString());
        } finally {
            return result;
        }
    }
    @Override
    @Transactional(readOnly = true)
    public int memberCount(){
        int result = -1;
        try{
            result = adminDao.memberCount();
        }catch (DataAccessException dae){
            daoException.printLog(dae.toString());
        }finally {
            return result;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getPermissions(){
        List<String> result = null;

        try{
            result=adminDao.getPermissions();
        }catch (DataAccessException dae){
            daoException.printLog(dae.toString());
        }finally {
            return result;
        }
    }

    @Override
    @Transactional
    public void update(String id, String[] perms) {
        try{
            adminDao.update(id,perms);
        }catch (DataAccessException dae){
            daoException.printLog(dae.toString());
        }
    }
}
