package cn.net.fusion.system.service;

import cn.net.fusion.system.entity.SysUser;
import com.alibaba.fastjson2.JSONObject;

import java.util.List;

/**
 * @ClassName ISysUserService
 * @Description 系统用户service接口
 * @Author ycl
 * @Date 2024/12/26 13:45
 * @Version 1.0
 */
public interface ISysUserService {

    /**
     * 查询所有用户信息（非删除标记的用户），分页查询
     *
     * @param pageNum      分页数
     * @param pageSize     分页大小
     * @param searchParams 检索条件
     * @return 所有用户
     */
    JSONObject getAllUser(int pageNum, int pageSize, JSONObject searchParams);

    /**
     * 获取回收站的用户，分页查询
     *
     * @param pageNum      分页数
     * @param pageSize     分页大小
     * @param searchParams 用户查询条件
     * @return 用户信息
     */
    JSONObject getUserListRecycle(int pageNum, int pageSize, JSONObject searchParams);

    /**
     * 校验用户名唯一
     *
     * @param userName 用户名
     * @return true 唯一 false 不唯一
     */
    boolean checkUserNameUnique(String userName);

    /**
     * 添加用户
     *
     * @param sysUser 用户信息
     * @return 结果
     */
    boolean addUser(SysUser sysUser) throws Exception;

    /**
     * 更新用户信息
     *
     * @param sysUser 用户信息
     * @return 结果
     */
    boolean updateUser(SysUser sysUser);

    /**
     * 批量删除用户信息（逻辑删除-放入回收站）
     *
     * @param userIds 用户ID集合
     * @return true｜false
     */
    boolean logicDeleteBatchUser(List<String> userIds);

    /**
     * 批量删除用户（物理删除）
     *
     * @param userIds 用户ID
     * @return 结果
     */
    boolean physicalDeleteBatchUsers(List<String> userIds);

    /**
     * 从回收站批量恢复用户
     *
     * @param userIds 用户ID
     * @return 恢复结果
     */
    boolean recoverFromRecycle(List<String> userIds);

    /**
     * 批量锁定用户
     *
     * @param userIds 用户ID
     * @return 锁定结果
     */
    boolean lockBatchUser(List<String> userIds);

    /**
     * 批量解锁用户
     *
     * @param userIds 用户ID
     * @return true ｜ false
     */
    boolean unlockBatchUser(List<String> userIds);

    /**
     * 重置用户密码(重置为123456)
     *
     * @param userId 用户ID
     * @return true | false
     */
    boolean resetPwd(String userId) throws Exception;

    /**
     * 修改用户密码
     *
     * @param userId 用户ID
     * @param newPwd 新密码
     * @return true| false
     */
    boolean modifyPwd(String userId, String newPwd) throws Exception;
}
