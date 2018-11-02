package project.ffboard.exception;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/*
로그만 남기는 용도이기 때문에 Exception을 상속받지 않았다.
 */
@Component
@Qualifier("DaoException")
public class DaoException implements FFException {
    @Override
    public void printLog(String message){
        // 로그 남기기
    }
}
