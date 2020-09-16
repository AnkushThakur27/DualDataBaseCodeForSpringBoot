
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DBConfig {
    private final static Logger LOGGER = LoggerFactory.getLogger(DBConfig.class);
    @Autowired
    private Environment env;
    
    @Bean(name = "firstSource")
    public DataSource firstDataSource() throws SQLException {
        LOGGER.info("Connecting to database...DB2");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource1.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource1.url"));
        dataSource.setUsername(env.getProperty("spring.datasource1.username"));
        dataSource.setPassword(env.getProperty("spring.datasource1.password"));
        return dataSource;
    }
    
    @Bean(name = "secondSource")
    @Primary
    public DataSource secondDataSource() throws SQLException {
        LOGGER.info("Connecting to database...Postgres");

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource2.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource2.url"));
        dataSource.setUsername(env.getProperty("spring.datasource2.username"));
        dataSource.setPassword(env.getProperty("spring.datasource2.password"));
        return dataSource;
    }
    
