package project.ffboard.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import project.ffboard.dto.Comment;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;

    public CommentDao(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction=new SimpleJdbcInsert(dataSource)
                .withTableName("comment").usingGeneratedKeyColumns("id","is_deleted");
    }

    public Long addComment(Comment comment){
        SqlParameterSource params = new BeanPropertySqlParameterSource(comment);
        System.out.println(comment.getContent());
        return insertAction.executeAndReturnKey(params).longValue();
    }
    public int deleteComment(Long id){
        String sql = "UPDATE comment SET is_deleted=:idDeleted WHERE id=:id";
        Map<String, Long> map = new HashMap<>();
        map.put("idDeleted", 1L);
        map.put("id", id);
        return jdbc.update(sql, map);
    }
    public int updateComment(Comment comment){
        String sql = "UPDATE comment" + " SET content=:content," + "upddate=now()"+
                " WHERE id=:id";
        Map<String, Object> map = new HashMap<>() ;
        map.put("id", comment.getId());
        map.put("content", comment.getContent());
        return jdbc.update(sql, map);
    }
    public List<Comment> getCommentList(Long articleId){
        String sql = "SELECT id, article_id, nick_name, content, group_id, depth_level, group_seq, " +
                "regdate, upddate, ip_address, member_id FROM comment WHERE article_id=:articleId";
        Map<String, Object> map = Collections.singletonMap("articleId", articleId);
        RowMapper<Comment> rowMapper = BeanPropertyRowMapper.newInstance(Comment.class);
        List<Comment> comments = jdbc.query(sql, map, rowMapper);

        return comments;
    }
}
