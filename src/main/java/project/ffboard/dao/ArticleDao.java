package project.ffboard.dao;

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
import java.util.Map;

@Repository
public class ArticleDao {
    private NamedParameterJdbcTemplate jdbc;
    private JdbcTemplate originJdbc;
    private SimpleJdbcInsert insertAction;

    public ArticleDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.originJdbc = new JdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("article").usingGeneratedKeyColumns("id");

    }

    public int addArticle(Article article) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(article);
        int result = insertAction.execute(params);
        String sql = "UPDATE article SET group_id=(SELECT LAST_INSERT_ID()) WHERE id=(SELECT LAST_INSERT_ID())";
        originJdbc.execute(sql);
        return result;
    }

    public int addArticleContent(ArticleContent articleContent) {
        SqlParameterSource params2 = new BeanPropertySqlParameterSource(articleContent);
        int result = insertAction.withTableName("article_content").execute(params2);
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

    public int updateArticle(Article article) {
        String sql = "UPDATE article SET title = :title, upddate = :upddate " +
                "WHERE id = :id";
        SqlParameterSource params = new BeanPropertySqlParameterSource(article);
        return jdbc.update(sql, params);
    }

    public int updateArticleContent(ArticleContent articleContent) {
        String sql = "UPDATE article_content SET content = :content WHERE article_id = :article_id";
        SqlParameterSource params = new BeanPropertySqlParameterSource(articleContent);
        return jdbc.update(sql, params);
    }

    public Article getArticle(Long id) {
        String sql = "SELECT id,title,hit,nick_name,group_id,depth_level,group_seq,regdate,upddate,category_id,ip_address,member_id,is_deleted FROM article WHERE id=:id";
        try{
            RowMapper<Article> rowMapper = BeanPropertyRowMapper.newInstance(Article.class);
            Map<String, Long> params = Collections.singletonMap("id", id);
            return jdbc.queryForObject(sql, params, rowMapper);
        }catch(Exception ex){
            return null;
        }
    }

    public ArticleContent getArticleContent(Long id) {
        String sql = "SELECT article_id,content FROM article_content WHERE article_id=:article_id";
        try{
            RowMapper<ArticleContent> rowMapper = BeanPropertyRowMapper.newInstance(ArticleContent.class);
            Map<String, ?> params = Collections.singletonMap("article_id", id);
            return jdbc.queryForObject(sql, params, rowMapper);
        }catch(Exception ex){
            return null;
        }
    }


    public JdbcTemplate getOriginJdbc() {
        return originJdbc;
    }

/*    public List<Article> getArticleList() {


    }

    //검색용.
    public List<Article> getArticleLsit() {

    }*/
}
