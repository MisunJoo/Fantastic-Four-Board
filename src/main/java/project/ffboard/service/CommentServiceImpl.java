package project.ffboard.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.ffboard.dao.CommentDao;
import project.ffboard.dto.Comment;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    @Transactional
    public int addComment(Comment comment) {
        return commentDao.addComment(comment);
    }

    @Override
    @Transactional
    public int deleteComment(Long id) {
        return commentDao.deleteComment(id);
    }

    @Override
    @Transactional
    public int updateComment(Comment comment) {
        return commentDao.updateComment(comment);
    }

    @Override
    public List<Comment> getCommentList(Long articleId) {
        return commentDao.getCommentList(articleId);
    }

    @Override
    public void modifyComment(Comment comment) {
        commentDao.modifyComment(comment);
    }
}
