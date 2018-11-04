package project.ffboard.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import project.ffboard.dto.Member;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AdminDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;

    public AdminDao(DataSource ds){
        this.jdbc = new NamedParameterJdbcTemplate(ds);
        this.insertAction = new SimpleJdbcInsert(ds).withTableName("memberPermission").usingGeneratedKeyColumns("id");
    }

    public List<Member> getMembers(int pg, String email, int limit) throws DataAccessException{
        String sql;
        RowMapper<Member> rowMapper = new BeanPropertyRowMapper<>().newInstance(Member.class);
        Map<String,Object> params = new HashMap<>();

        if(!email.equals("")) {
            sql = "SELECT id, email, nick_name FROM member WHERE email=:email";
            params.put("email", email);
        }
        else {
            sql = "SELECT id, email, nick_name FROM member LIMIT :pg,:limit";
            params.put("pg",pg*limit - limit+1);
            params.put("limit",pg*limit);
        }
        return jdbc.query(sql, params, rowMapper);
    }

    public List<String> getPermissions(){
        String sql = "SELECT name FROM permission";
        return jdbc.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("name");
            }
        });
    }
}
