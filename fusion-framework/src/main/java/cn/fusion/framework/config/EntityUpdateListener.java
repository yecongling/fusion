package cn.fusion.framework.config;

import cn.fusion.framework.core.BaseEntity;
import cn.fusion.framework.core.SysOpr;
import cn.fusion.framework.utils.ServletUtils;
import cn.fusion.framework.utils.SpringContextUtils;
import com.mybatisflex.annotation.UpdateListener;

import java.time.LocalDateTime;

/**
 * @ClassName EntityUpdateListener
 * @Description 实体类更新数据时的数据填充，填充更新人、更新时间，该功能只有使用mybatis-flex的BaseMapper的时候才会触发，
 * 使用mybatis的xml mapper插入数据或者通过Db + Row中插入数据，不会触发该行为
 * @Author ycl
 * @Date 2024/12/14 10:59
 * @Version 1.0
 */
public class EntityUpdateListener implements UpdateListener {

    // 获取操作员
    private final ServletUtils servletUtils = SpringContextUtils.getBean(ServletUtils.class);

    /**
     * @param o
     */
    @Override
    public void onUpdate(Object o) {
        BaseEntity baseEntity = (BaseEntity) o;
        SysOpr sysOpr = servletUtils.getSysOpr();

        baseEntity.setUpdateBy(sysOpr.getUserId());
        baseEntity.setUpdateTime(LocalDateTime.now());
    }
}
