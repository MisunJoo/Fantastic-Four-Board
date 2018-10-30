package project.ffboard.service;

import org.springframework.stereotype.Service;
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
    public int addComment(Comment comment) {
        return commentDao.addComment(comment);
    }

    @Override
    public int deleteComment(Long id) {
        return commentDao.deleteComment(id);
    }

    @Override
    public int updateComment(Comment comment) {
        return commentDao.updateComment(comment);
    }

    @Override
    public List<Comment> getCommentList(Long articleId) {
        return commentDao.getCommentList(articleId);
    }
}
