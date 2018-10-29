package project.ffboard.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DBConfig {
<<<<<<< Updated upstream
    private String driverClassName = "org.mariadb.jdbc.Driver";
    private String url = "jdbc:mariadb://localhost:3306/ffboard";
    private String username = "siyoon";
    private String password = "1234";
=======
    private String driverClassName = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/ffboard?useUnicode=true&characterEncoding=utf8";
    private String username = "mysql";
    private String password = "mysql";
>>>>>>> Stashed changes


    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }


    @Bean
    public PlatformTransactionManager transactionManger(DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }

    private DatabasePopulator createDatabasePopulator() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setContinueOnError(true);
        databasePopulator.addScript(new ClassPathResource("schema.sql"));
        return databasePopulator;
    }
}

