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
import project.ffboard.dto.Member;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

@Repository
public class MemberDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private JdbcTemplate jdbcTemplate;

    public MemberDao(DataSource datasource) {
        this.jdbc = new NamedParameterJdbcTemplate(datasource);
        this.insertAction = new SimpleJdbcInsert(datasource)
                .withTableName("member")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }

    public Long signUp(Member member) throws DataAccessException {
        String sql = "SELECT * FROM member WHERE email=:email OR nick_name=:nickName";
        Map<String, Object> map = new HashMap<>();
        map.put("email", member.getEmail());
        map.put("nickName", member.getNickName());
        RowMapper<Member> rowMapper = BeanPropertyRowMapper.newInstance(Member.class);
        if (jdbc.query(sql, map, rowMapper).size() > 0) {
            sql = "SELECT * FROM member WHERE email=:email";
            if (jdbc.query(sql, map, rowMapper).size() > 0) {
                return -1L;// 이메일 중복
            } else {
                return -2L; // 닉네임중복
            }
        }
        SqlParameterSource params = new BeanPropertySqlParameterSource(member);
        return insertAction.executeAndReturnKey(params).longValue();
    }

    public Member login(Member member) throws DataAccessException {
        String sql = "SELECT id,email,nick_name  FROM member WHERE email=:email AND password=:password";
        Map<String, String> map = new HashMap<>();
        map.put("email", member.getEmail());
        map.put("password", member.getPassword());

        RowMapper<Member> rowMapper = BeanPropertyRowMapper.newInstance(Member.class);
        return jdbc.queryForObject(sql, map, rowMapper);
    }
}
