package cn.fusion.framework.config;

import cn.dev33.satoken.stp.StpUtil;
import cn.fusion.framework.netty.service.IPushMsgService;
import cn.fusion.framework.netty.service.PushMsgServiceImpl;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.mybatisflex.core.audit.AuditMessage;
import com.mybatisflex.core.audit.MessageReporter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName FlexSqlMessageReporter
 * @Description sql发送器（用于将执行的SQL通过websocket发送到前端进行监测）
 * @Author ycl
 * @Date 2025/3/8 10:43
 * @Version 1.0
 */
@Component
public class FlexSqlMessageReporter implements MessageReporter {

    private final IPushMsgService pushMsgService;
    @Autowired
    public FlexSqlMessageReporter(PushMsgServiceImpl pushMsgService) {
        this.pushMsgService = pushMsgService;
    }

    @Override
    public void sendMessages(List<AuditMessage> messages) {
        // 获取当前调用的用户会话
        String tokenValue = StpUtil.getTokenValue();
        if (StringUtils.isEmpty(tokenValue)) {
            return;
        }
        // 推送消息
        JSONArray jsonArray = new JSONArray();
        messages.forEach(message -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("SQL", message.getFullSql());
            jsonObject.put("times", message.getElapsedTime() + "ms");
            jsonObject.put("business", "logSQL");
            jsonArray.add(jsonObject);
        });
        pushMsgService.pushMsgToOne(tokenValue, jsonArray.toJSONString());
    }
}
