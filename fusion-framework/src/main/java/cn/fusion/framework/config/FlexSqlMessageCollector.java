package cn.fusion.framework.config;

import cn.dev33.satoken.stp.StpUtil;
import cn.fusion.framework.netty.service.IPushMsgService;
import cn.fusion.framework.netty.service.PushMsgServiceImpl;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.mybatisflex.core.audit.AuditMessage;
import com.mybatisflex.core.audit.MessageCollector;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName FlexSqlMessageReporter
 * @Description sql发送器（用于将执行的SQL通过websocket发送到前端进行监测）
 * @Author ycl
 * @Date 2025/3/8 10:43
 * @Version 1.0
 */
@Component
public class FlexSqlMessageCollector implements MessageCollector {

    private final IPushMsgService pushMsgService;

    @Autowired
    public FlexSqlMessageCollector(PushMsgServiceImpl pushMsgService) {
        this.pushMsgService = pushMsgService;
    }

    @Override
    public void collect(AuditMessage message) {
        // 获取当前调用的用户会话
        String tokenValue = StpUtil.getTokenValue();
        if (StringUtils.isEmpty(tokenValue)) {
            return;
        }
        // 推送消息
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("SQL", message.getFullSql());
        System.out.println("执行SQL：" + message.getQuery());
        System.out.println("执行耗时：" + message.getElapsedTime() + "ms");
        System.out.println("执行参数：" + message.getQueryParams());
        jsonObject.put("times", message.getElapsedTime() + "ms");
        jsonObject.put("business", "logSQL");
        jsonArray.add(jsonObject);
        pushMsgService.pushMsgToOne(tokenValue, jsonArray.toJSONString());
    }
}
