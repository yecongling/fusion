package cn.fusion.framework.config;

import cn.fusion.framework.utils.SnowFlakeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName SnowflakeConfig
 * @Description 雪花算法的bean生成
 * @Author ycl
 * @Date 2024/11/4 10:14
 * @Version 1.0
 */
@Configuration
public class SnowflakeConfig {
    @Bean
    public SnowFlakeGenerator snowFlakeGenerator() {
        long workerId = 1L;
        long datacenterId = 1L;
        return new SnowFlakeGenerator(workerId, datacenterId);
    }
}
