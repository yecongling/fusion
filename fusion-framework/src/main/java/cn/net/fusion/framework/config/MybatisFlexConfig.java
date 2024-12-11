package cn.net.fusion.framework.config;

import com.mybatisflex.core.audit.AuditManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MybatisFlexConfig
 * @Description mybatis-flex配置 配置打印
 * @Author 叶丛林
 * @Date 2024/12/9 21:27
 * @Version 1.0
 **/
@Configuration
public class MybatisFlexConfig {
    private static final Logger logger = LoggerFactory.getLogger("mybatis-flex-sql");

    @Value("${mybatis.sqlLog:true}")
    private boolean interceptorEnabled;

    public MybatisFlexConfig() {
        // 根据配置来决定是否开启sql记录
        AuditManager.setAuditEnable(interceptorEnabled);
        // 设置SQL审计收集器
        AuditManager.setMessageCollector(auditMessage -> logger.info("{},{}ms", auditMessage.getFullSql()
                , auditMessage.getElapsedTime()));
    }
}
