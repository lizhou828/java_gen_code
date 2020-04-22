package ${basepackage}.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by lizhou on 2015/12/11
 */

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "${basepackage}.mapper",sqlSessionFactoryRef = "sqlSessionFactoryBean")
@EnableAutoConfiguration
public class DataBaseConfig {

    private final Logger log = LoggerFactory.getLogger(DataBaseConfig.class);

    @Value(${r'"${'}spring.datasource.driverClassName${r'}"'})
    private String driver;

    @Value(${r'"${'}spring.datasource.url${r'}"'} )
    private String url;

    @Value(${r'"${'}spring.datasource.username${r'}"'})
    private String username;

    @Value(${r'"${'}spring.datasource.password${r'}"'})
    private String password;



    @Bean(name="dataSource")
    @Primary
    @ConfigurationProperties(prefix = "jdbc")
    public DataSource dataSource() {
        log.debug("Configuring Datasource");
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(this.driver);
        druidDataSource.setUrl(this.url);
        druidDataSource.setUsername(this.username);
        druidDataSource.setPassword(this.password);
        return druidDataSource;
    }

    @Bean
    @Primary
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }


}
