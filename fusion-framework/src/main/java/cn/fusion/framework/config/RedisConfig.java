package cn.fusion.framework.config;

import cn.fusion.framework.security.EncryptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @ClassName RedisConfig
 * @Description redis的配置类，配置序列化方式
 * @Author 叶丛林
 * @Date 2024/11/2 21:06
 * @Version 1.0
 **/
@Component
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.password}")
    private String encryptedPassword;

    @Value("${spring.data.redis.port}")
    private Integer port;

    @Value("${spring.data.redis.database}")
    private Integer database;

    /*  连接池相关信息  */

    @Value("${spring.data.redis.lettuce.pool.max-idle}")
    private Integer maxIdle;

    @Value("${spring.data.redis.lettuce.pool.max-active}")
    private Integer maxActive;

    @Value("${spring.data.redis.lettuce.pool.max-idle}")
    private Integer minIdle;

    // 从环境变量获取秘钥
    private static final String SECRET_KEY = System.getenv("FUSION_SECRET_KEY");

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() throws Exception {
        // 解密密码
        String password = EncryptionUtils.decrypt(encryptedPassword, SECRET_KEY);

        // 配置redis连接工厂
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(host, port);

        configuration.setPassword(password);
        configuration.setDatabase(database);

        // 配置连接池
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(configuration);
        connectionFactory.setValidateConnection(true);
        return connectionFactory;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> getRedisTemplate(LettuceConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        // 设置key序列化方式string，RedisSerializer.string() 等价于 new StringRedisSerializer()
        redisTemplate.setKeySerializer(RedisSerializer.string());
        // 设置value的序列化方式json，使用GenericJackson2JsonRedisSerializer替换默认序列化，RedisSerializer.json() 等价于 new GenericJackson2JsonRedisSerializer()
        redisTemplate.setValueSerializer(RedisSerializer.json());
        // 设置hash的key的序列化方式
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        // 设置hash的value的序列化方式
        redisTemplate.setHashValueSerializer(RedisSerializer.json());
        // 使配置生效
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
