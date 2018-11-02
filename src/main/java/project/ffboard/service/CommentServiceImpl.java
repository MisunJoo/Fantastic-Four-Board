package project.ffboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.ffboard.dao.CommentDao;
import project.ffboard.dto.Comment;
import project.ffboard.exception.FFException;

import java.sql.SQLException;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao;
    @Autowired
    @Qualifier(value = "DaoException")
    private FFException daoException;

    public CommentServiceImpl(CommentDao commentDao, FFException daoException) {
        this.commentDao = commentDao;
        this.daoException = daoException;
    }

    @Override
    @Transactional
    public Long addComment(Comment comment) {
        Long result = null;

        // 예외처리 Controller or Service 어디가 좋을지?
        // Service에 해주면 Controller에서 if문으로 체크를 한번 더 해줘야함.
        try {
            result = commentDao.addComment(comment);
        } catch (DataAccessException dae) {
            daoException.printLog(dae.toString());
        } finally {
            return result;
        }
    }

    @Override
    @Transactional
    public int deleteComment(Long id) {
        int result = -1;

        try {
            result = commentDao.deleteComment(id);
        } catch (DataAccessException dae) {
            daoException.printLog(dae.toString());
        } finally {
            return result;
        }
    }

    @Override
    @Transactional
    public int updateComment(Comment comment) {
        return commentDao.updateComment(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getCommentList(Long articleId) {
        List<Comment> result = null;
        try {
            result = commentDao.getCommentList(articleId);
        } catch (DataAccessException dae) {
            daoException.printLog(dae.toString());
        } finally {
            return result;
        }
    }

    @Override
    @Transactional
    public int modifyComment(Comment comment) {
        int result = -1;
        try {
            result = commentDao.modifyComment(comment);
        } catch (DataAccessException dae) {
            daoException.printLog(dae.toString());
        } finally {
            return result;
        }
    }
}
