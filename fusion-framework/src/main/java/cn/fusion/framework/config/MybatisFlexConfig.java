package cn.fusion.framework.config;

import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.audit.AuditManager;
import com.mybatisflex.core.logicdelete.LogicDeleteProcessor;
import com.mybatisflex.core.logicdelete.impl.BooleanLogicDeleteProcessor;
import com.mybatisflex.spring.boot.MyBatisFlexCustomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MybatisFlexConfig
 * @Description mybatis-flex配置 配置打印
 * @Author 叶丛林
 * @Date 2024/12/9 21:27
 * @Version 1.0
 **/
@Configuration
public class MybatisFlexConfig implements MyBatisFlexCustomizer {
    private static final Logger logger = LoggerFactory.getLogger("mybatis-flex-sql");

    @Value("${mybatis.sqlLog:true}")
    private boolean interceptorEnabled;

    private final FlexSqlMessageReporter flexSqlMessageReporter;
    @Autowired
    public MybatisFlexConfig(FlexSqlMessageReporter flexSqlMessageReporter) {
        this.flexSqlMessageReporter = flexSqlMessageReporter;
    }

    @Override
    public void customize(FlexGlobalConfig globalConfig) {
        // 根据配置来决定是否开启sql记录
        AuditManager.setAuditEnable(interceptorEnabled);
        // 设置SQL审计收集器
        AuditManager.setMessageReporter(flexSqlMessageReporter);
    }

    /**
     * 逻辑删除处理器
     *
     * @return 逻辑删除处理器
     */
    @Bean
    public LogicDeleteProcessor logicDeleteProcessor() {
        return new BooleanLogicDeleteProcessor();
    }
}
