package cn.fusion.framework.config;

import cn.fusion.framework.security.EncryptionUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @ClassName HikariDataSourceConfig
 * @Description Hikari数据库连接池的配置信息
 * @Author ycl
 * @Date 2024/12/16 10:19
 * @Version 1.0
 */
@Configuration
public class HikariDataSourceConfig {

    /*  数据库的连接配置  */

    @Value("${spring.datasource.url}")
    private String encryptedUrl;

    @Value("${spring.datasource.username}")
    private String encryptedUsername;

    @Value("${spring.datasource.password}")
    private String encryptedPassword;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    /*   连接池的配置信息   */

    @Value("${spring.datasource.hikari.pool-name}")
    private String poolName;

    @Value("${spring.datasource.hikari.minimum-idle}")
    private Integer minIdle;

    @Value("${spring.datasource.hikari.idle-timeout}")
    private Integer idleTimeout;

    @Value("${spring.datasource.hikari.max-lifetime}")
    private Integer maxLifetime;

    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private Integer maxPoolSize;

    @Value("${spring.datasource.hikari.connection-timeout}")
    private Integer connectionTimeout;

    @Value("${spring.datasource.hikari.connection-test-query}")
    private String connectionTestQuery;

    // 秘钥key，从环境变量获取
    private static final String SECRET_KEY = System.getenv("FUSION_SECRET_KEY");
    /**
     * 数据库连接池的配置
     */
    @Bean
    public DataSource dataSource() throws Exception{
        String url = EncryptionUtils.decrypt(encryptedUrl, SECRET_KEY);
        String username = EncryptionUtils.decrypt(encryptedUsername, SECRET_KEY);
        String password = EncryptionUtils.decrypt(encryptedPassword, SECRET_KEY);

        // 配置数据源
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driverClassName);
        config.setPoolName(poolName);
        config.setMinimumIdle(minIdle);
        config.setIdleTimeout(idleTimeout);
        config.setMaxLifetime(maxLifetime);
        config.setMaximumPoolSize(maxPoolSize);
        config.setConnectionTimeout(connectionTimeout);
        config.setConnectionTestQuery(connectionTestQuery);
        return new HikariDataSource(config);
    }
}
