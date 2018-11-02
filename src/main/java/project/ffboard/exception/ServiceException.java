package project.ffboard.exception;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@Qualifier("ServiceException")
public class ServiceException implements FFException{
    @Override
    public void printLog(String message) {

    }
}
