package project.ffboard.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import project.ffboard.dto.Comment;
import project.ffboard.dto.CommentCounting;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private JdbcTemplate jdbcTemplate;

    public CommentDao(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction=new SimpleJdbcInsert(dataSource)
                .withTableName("comment")
                .usingGeneratedKeyColumns("id").usingColumns("article_id","nick_name","content", "group_id","depth_level", "group_seq", "ip_address","member_id");
        this.jdbcTemplate=new JdbcTemplate(dataSource);
    }

    public Long addComment(Comment comment, CommentCounting commentCounting) throws DataAccessException {
        Boolean isReply = comment.getGroupId() != null && comment.getDepthLevel() > 0 && comment.getGroupSeq() > 0;
        System.out.println("Welcome to the Dao");

        // 나중에 시윤이형네꺼 카피
        if (isReply) {
            String sql = "UPDATE comment SET group_seq = group_seq + 1 WHERE group_id = :groupId AND group_seq >= :groupSeq";
            Map<String, Number> map = new HashMap<>();
            map.put("groupId", comment.getGroupId());
            map.put("groupSeq", comment.getGroupSeq());
            jdbc.update(sql, map);
        }

        SqlParameterSource params = new BeanPropertySqlParameterSource(comment);
        Long result = insertAction.executeAndReturnKey(params).longValue();

        if (!isReply) {
            String sql = "UPDATE comment SET group_id=(SELECT LAST_INSERT_ID()) " +
                    "WHERE id=(SELECT LAST_INSERT_ID())";
            jdbcTemplate.execute(sql);
        }

        //Table comment_counting

        SqlParameterSource countingParams = new BeanPropertySqlParameterSource(commentCounting);
        Long countingId = insertAction.executeAndReturnKey(countingParams).longValue();

        String countingSql = "UPDATE comment_counting SET counting = counting +1 WHERE article_id = :articleId";
        Map<String, Long> map = Collections.singletonMap("articleId", countingId);
        jdbc.update(countingSql, map);

        return result;
    }

    public int deleteComment(Long id) throws DataAccessException {
        String sql = "UPDATE comment SET is_deleted=:isDeleted WHERE id=:id";
        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted", true);
        map.put("id", id);
        return jdbc.update(sql, map);
    }

    public int updateComment(Comment comment) throws DataAccessException{
        String sql = "UPDATE comment" + " SET content=:content," + "upddate=now()"+
                " WHERE id=:id";
        Map<String, Object> map = new HashMap<>() ;
        map.put("id", comment.getId());
        map.put("content", comment.getContent());
        return jdbc.update(sql, map);
    }

    public List<Comment> getCommentList(Long articleId, int page, int posts)throws DataAccessException {
        String sql = "SELECT id, article_id, nick_name, content, group_id, depth_level, group_seq, " +
                "regdate, upddate, ip_address, member_id, is_deleted " +
                "FROM comment WHERE article_id=:articleId " +
                "ORDER By group_id DESC, group_seq ASC " +
                "LIMIT :start, :end ";

        int start = page * posts - (posts - 1) - 1;
        int end = posts;
        Map<String, Object> map = new HashMap<>();
        map.put("articleId", articleId);
        map.put("start", start);
        map.put("end", end);

        RowMapper<Comment> rowMapper = BeanPropertyRowMapper.newInstance(Comment.class);
        List<Comment> comments = jdbc.query(sql, map, rowMapper);


        return comments;
    }

    public int getCount(Long articleId) throws DataAccessException {
        String sql = "SELECT COUNT(*) FROM comment WHERE article_id=:articleId";
        Map<String, Object> map = Collections.singletonMap("articleId", articleId);


        return jdbc.queryForObject(sql, map, new RowMapper<Integer>(){
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt(1);
            }
        });
    }

    public int modifyComment(Comment comment) throws DataAccessException {
        String sql = "UPDATE comment SET content=:content, upddate=now() " +
                "WHERE id=:id";
        SqlParameterSource params = new BeanPropertySqlParameterSource(comment);

        return jdbc.update(sql,params);
    }
}
