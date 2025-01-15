package cn.net.fusion.system.service;

import cn.net.fusion.system.entity.SysUser;

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
     * 查询所有用户信息（非删除标记的用户）
     *
     * @param sysUser 检索条件
     * @return 所有用户
     */
    List<SysUser> getAllUser(SysUser sysUser);

    /**
     * 获取回收站的用户
     *
     * @param sysUser 用户查询条件
     * @return 用户信息
     */
    List<SysUser> getUserListRecycle(SysUser sysUser);

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
    boolean addUser(SysUser sysUser);

    /**
     * 更新用户信息
     *
     * @param sysUser 用户信息
     * @return 结果
     */
    boolean updateUser(SysUser sysUser);

    /**
     * 批量删除用户信息（假删除-放入回收站）
     *
     * @param userIds 用户ID集合
     * @return true｜false
     */
    boolean deleteBatchUser(List<Long> userIds);

    /**
     * 批量锁定用户
     *
     * @param userIds 用户ID
     * @return 锁定结果
     */
    boolean lockBatchUser(List<Long> userIds);

    /**
     * 批量解锁用户
     *
     * @param userIds 用户ID
     * @return true ｜ false
     */
    boolean unlockBatchUser(List<Long> userIds);

    /**
     * 重置用户密码(重置为123456)
     *
     * @param sysUser 用户
     * @return true | false
     */
    boolean resetPwd(SysUser sysUser);

    /**
     * 更新用户密码
     *
     * @param userId 用户ID
     * @param newPwd 新密码
     * @return true| false
     */
    boolean resetPwd(String userId, String newPwd);
}
