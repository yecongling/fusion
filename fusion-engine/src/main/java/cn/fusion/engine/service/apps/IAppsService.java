package cn.fusion.engine.service.apps;

import cn.fusion.engine.dto.app.AppQuery;
import cn.fusion.engine.entity.apps.App;
import cn.fusion.framework.core.SysOpr;
import com.alibaba.fastjson2.JSONObject;
import org.apache.camel.ServiceStatus;

import java.util.List;

/**
 * @ClassName IProjectService
 * @Description 应用service
 * @Author ycl
 * @Date 2025/1/22 11:02
 * @Version 1.0
 */
public interface IAppsService {

    /**
     * 检索应用（目前参数支持name、type进行检索）
     *
     * @param appQuery 检索条件
     * @param sysOpr       操作人信息
     * @return 应用集合
     */
    List<App> getApps(AppQuery appQuery, SysOpr sysOpr);

    /**
     * 新增应用
     *
     * @param app 应用
     * @return true | false
     */
    boolean addApp(App app);

    /**
     * 更新应用基础信息
     *
     * @param app 应用
     * @return true | false
     */
    boolean updateApp(App app);

    /**
     * 检测服务运行状态
     * @param projectId 应用id
     */
    ServiceStatus checkServiceStatus(String projectId);

    /**
     * 删除应用
     *
     * @param app 应用
     * @return true | false
     */
    boolean deleteApp(App app);

    /**
     * 更新服务状态（启用服务、停止服务）
     *
     * @param app 应用
     * @return 结果
     */
    Object changeServiceStatus(App app);

    /**
     * 导出应用
     *
     * @param appId 应用id
     * @return 结果
     */
    Object exportApp(String appId);

    /**
     * 导入应用
     *
     * @param app 应用数据（目前暂定为JSON结构）
     * @return 结果
     */
    Object importApp(JSONObject app);
}
