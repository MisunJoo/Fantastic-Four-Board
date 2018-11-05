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
import java.util.Collections;
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

    // permission 테이블에 어떤 row가 있는지 받아서 관리자 페이지 테이블에 출력용
    public List<String> getPermissions() throws DataAccessException{
        String sql = "SELECT name FROM permission";
        return jdbc.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("name");
            }
        });
    }

    // 각 회원이 가지고 있는 권한을 구해서 MemberDTO의 Set에 넣어준다
    public List<String> getMemPerm(Long id) throws DataAccessException {
        String sql = "SELECT perm_name FROM member_permission WHERE member_id=:id";
        Map<String, Long> params = Collections.singletonMap("id",id);
        return jdbc.query(sql, params, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("perm_name");
            }
        });
    }


    /**
     * 지우고 다시 삽입하는 이유?
     * - 조회해서 해당 권한이 있는지 확인한 다음 삭제하고 추가하는 방법은 복잡하고 오히려 시간이 더 걸릴수도 있을거 같다.
     * - Exception 무시하고 그냥 추가, 삭제 하는 방법도 있지만 좋지 않은거 같다.
     */
    public void update(String id, String[] perms) throws DataAccessException {
        String sql = "DELETE from member_permission WHERE member_id=:id";
        Map<String, String> params = new HashMap<>();
        params.put("id",id);
        jdbc.update(sql, params);

        for(String perm : perms){
            sql="INSERT INTO member_permission(member_id, perm_name) " +
                    "VALUES (:id, :perm)";
            params.put("perm",perm);
            jdbc.update(sql,params);
            params.remove(perm);
        }
    }
}
