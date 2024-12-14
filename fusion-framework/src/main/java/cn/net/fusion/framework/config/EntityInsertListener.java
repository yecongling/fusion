package cn.net.fusion.framework.config;

import cn.net.fusion.framework.core.BaseEntity;
import cn.net.fusion.framework.core.SysOpr;
import cn.net.fusion.framework.utils.ServletUtils;
import cn.net.fusion.framework.utils.SpringContextUtils;
import com.mybatisflex.annotation.InsertListener;

import java.util.Date;

/**
 * @ClassName EntityInsertListener
 * @Description 实体类插入数据时的数据填充，填充创建人、创建时间、更新人、更新时间
 * @Author ycl
 * @Date 2024/12/14 10:39
 * @Version 1.0
 */
public class EntityInsertListener implements InsertListener {

    // 获取操作员
    private final ServletUtils servletUtils = SpringContextUtils.getBean(ServletUtils.class);

    /**
     * @param o 对象
     */
    @Override
    public void onInsert(Object o) {
        BaseEntity baseEntity = (BaseEntity) o;
        SysOpr sysOpr = servletUtils.getSysOpr();

        baseEntity.setCreateBy(sysOpr.getUserId());
        baseEntity.setUpdateBy(sysOpr.getUserId());
        baseEntity.setCreateTime(new Date());
        baseEntity.setUpdateTime(new Date());
    }
}
