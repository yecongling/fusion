package cn.net.fusion.framework.service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * @ClassName RateLimiterService
 * @Description 限流service，用于业务逻辑的限流，保障服务安全
 * @Author 叶丛林
 * @Date 2025/1/20 21:49
 * @Version 1.0
 **/
@Service
public class RateLimiterService {

    // @TODO 这里的配置数据后续考虑使用系统参数
    private static final int CAPACITY = 100; // 每分钟最大请求数
    private static final long REFILL_INTERVAL = 60; // 1分钟

    /**
     * 创建限流桶
     *
     * @return 返回限流桶
     */
    private Bucket createBucket() {
        Bandwidth limit = Bandwidth.simple(CAPACITY, Duration.ofSeconds(REFILL_INTERVAL));
        // 限制流速
        return Bucket.builder().addLimit(limit).build();
    }

    /**
     * 尝试进行获取
     *
     * @param key key
     * @return true｜false
     */
    public boolean tryConsume(String key) {
        // 获取存储桶
        Bucket bucket = createBucket();
        // 判断是否可以获取令牌
        ConsumptionProbe consumptionProbe = bucket.tryConsumeAndReturnRemaining(1);
        return consumptionProbe.isConsumed();
    }
}
