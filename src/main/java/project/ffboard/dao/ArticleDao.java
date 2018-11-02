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
import project.ffboard.dto.Article;
import project.ffboard.dto.ArticleContent;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleDao {
    private NamedParameterJdbcTemplate jdbc;
    private JdbcTemplate originJdbc;
    private SimpleJdbcInsert insertActionArticle;
    private SimpleJdbcInsert insertActionArticleContent;

    public ArticleDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.originJdbc = new JdbcTemplate(dataSource);
        this.insertActionArticle = new SimpleJdbcInsert(dataSource).withTableName("article").usingGeneratedKeyColumns("id")
                .usingColumns("title","nick_name","group_id","depth_level","group_seq","category_id", "ip_address","member_id");
        this.insertActionArticleContent = new SimpleJdbcInsert(dataSource).withTableName("article_content");
    }

    public int arrangeGroupSeq(Long groupId, int groupSeq){
        String sql = "UPDATE article SET group_seq = group_seq + 1 WHERE group_id = :groupId AND group_seq >= :groupSeq";
        Map<String, Number> map = new HashMap<>();
        map.put("groupId", groupId);
        map.put("groupSeq", groupSeq);
        return jdbc.update(sql, map);
    }

    public Long insertArticle(Article article) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(article);
        return insertActionArticle.executeAndReturnKey(params).longValue();
    }

    public void insertGroupId() {
        String sql = "UPDATE article SET group_id=(SELECT LAST_INSERT_ID()) WHERE id=(SELECT LAST_INSERT_ID())";
        originJdbc.execute(sql);
    }

    public int insertArticleContent(ArticleContent articleContent) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(articleContent);
        int result = insertActionArticleContent.execute(params);
        return result;
    }


    public int updateCount(Long id){
        String sql = "UPDATE article SET hit = hit + 1 WHERE id = :id";
        Map<String, Long> map = Collections.singletonMap("id", id);
        return jdbc.update(sql, map);
    }

    public int deleteArticle(Long id) {
        String sql = "UPDATE article SET is_deleted=TRUE WHERE id = :id";
        Map<String, Long> map = Collections.singletonMap("id",id);
        return jdbc.update(sql, map);
    }

    public Long updateArticle(Article article) {
        String sql = "UPDATE article SET title = :title, nick_name=:nickName, upddate = :upddate, ip_address = :ipAddress " +
                "WHERE id = :id";
        SqlParameterSource params = new BeanPropertySqlParameterSource(article);
        jdbc.update(sql, params);
        return article.getId();
    }

    public int updateArticleContent(ArticleContent articleContent) {
        String sql = "UPDATE article_content SET content = :content WHERE article_id = :articleId";
        SqlParameterSource params = new BeanPropertySqlParameterSource(articleContent);
        return jdbc.update(sql, params);
    }

    public Article getArticle(Long id) {
        String sql = "SELECT id,title,hit,nick_name,group_id,depth_level,group_seq,regdate,"
                +"upddate,category_id,ip_address,member_id,is_deleted "
                +"FROM article WHERE id=:id";
        try{
            RowMapper<Article> rowMapper = BeanPropertyRowMapper.newInstance(Article.class);
            Map<String, Long> params = Collections.singletonMap("id", id);
            return jdbc.queryForObject(sql, params, rowMapper);
        }catch(DataAccessException e){
            return null;
        }
    }

    public ArticleContent getArticleContent(Long id) {
        String sql = "SELECT article_id, content FROM article_content WHERE article_id=:articleId";

        try {
            RowMapper<ArticleContent> rowMapper = BeanPropertyRowMapper.newInstance(ArticleContent.class);
            Map<String, Long> params = Collections.singletonMap("articleId", id);
            return jdbc.queryForObject(sql, params, rowMapper);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public List<Article> getArticleList(int categoryId, int start, int limit) {
        String sql = "SELECT id,title,hit,nick_name,group_id,depth_level,group_seq,regdate,"
                +"upddate,category_id,ip_address,member_id,is_deleted FROM article WHERE category_id=:categoryId "
                +"ORDER BY group_id DESC, group_seq ASC LIMIT :start , :limit";
        RowMapper<Article> rowMapper =  BeanPropertyRowMapper.newInstance(Article.class);

        Map<String, Integer> params = new HashMap();
        params.put("categoryId", Integer.valueOf(categoryId));
        params.put("start", Integer.valueOf(start));
        params.put("limit", Integer.valueOf(limit));
        try {
            return jdbc.query(sql,params,rowMapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 검색어를 적용한 게시판 리스트
     * @param categoryId 검색을 원하는 카테고리의 index id
     * @param start 검색을 시작할 인덱스
     * @param limit 검색 리스트의 한도 갯수
     * @param searchType 검색타입으로 "제목","내용","이름","제목+내용"만을 받는다.
     * @param searchWord 사용자가 입력한 검색어
     * @return
     */
    public List<Article> getArticleList(int categoryId, int start, int limit, String searchType, String searchWord) {
        searchWord = "%" + searchWord + "%";
        RowMapper<Article> rowMapper =  BeanPropertyRowMapper.newInstance(Article.class);
        String sql = "SELECT art.id,art.title, art.hit,art.nick_name, art.group_id, art.depth_level, art.group_seq, "
                +"art.regdate, art.upddate, art.category_id, art.ip_address, art.member_id, art.is_deleted, artcon.content "
                +"FROM article art LEFT OUTER JOIN article_content artcon ON art.id = artcon.article_id  WHERE art.category_id=:categoryId AND ";

        if (searchType.equals("제목")) {
            sql += "art.title LIKE :searchWord ";
        } else if (searchType.equals("내용")) {
            sql += "artcon.content LIKE :searchWord ";
        } else if (searchType.equals("이름")) {
            sql += "art.nick_name LIKE :searchWord ";
        } else if (searchType.equals("제목+내용")) {
            sql += "art.title LIKE :searchWord OR artcon.content LIKE :searchWord ";
        } else {
            return null;
        }

        sql+="ORDER BY art.group_id DESC, art.group_seq ASC LIMIT :start , :limit";

        Map<String, Object> params = new HashMap();
        params.put("categoryId", Integer.valueOf(categoryId));
        params.put("start", Integer.valueOf(start));
        params.put("limit", Integer.valueOf(limit));
        params.put("searchWord", searchWord);

        try {
            return jdbc.query(sql,params,rowMapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
