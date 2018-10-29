package project.ffboard.dao;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import project.ffboard.dto.Comment;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CommentDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;

    public CommentDao(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction=new SimpleJdbcInsert(dataSource)
                .withTableName("comment").usingGeneratedKeyColumns("id");
    }

    public Long addComment(Comment comment){
        SqlParameterSource params = new BeanPropertySqlParameterSource(comment);

        return insertAction.executeAndReturnKey(params).longValue();
    }
    public int deleteComment(Long id){

        return 1;
    }
    public int updateComment(Long id){

        return 1;
    }
    public List<Comment> getCommentList(Long articleId){

        return null;
    }
}
