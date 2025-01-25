package cn.net.fusion.system.service.impl;

import cn.net.fusion.framework.constant.CommonConstant;
import cn.net.fusion.framework.core.SysOpr;
import cn.net.fusion.framework.utils.PasswordUtils;
import cn.net.fusion.framework.utils.ServletUtils;
import cn.net.fusion.system.entity.SysUser;
import cn.net.fusion.system.mapper.SysUserMapper;
import cn.net.fusion.system.service.ISysUserService;
import com.alibaba.fastjson2.JSONObject;
import com.mybatisflex.core.logicdelete.LogicDeleteManager;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.row.Db;
import com.mybatisflex.core.util.UpdateEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName SysUserServiceImpl
 * @Description 用户service实现
 * @Author ycl
 * @Date 2025/1/25 14:03
 * @Version 1.0
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    private final SysUserMapper sysUserMapper;
    private final ServletUtils servletUtils;

    @Autowired
    public SysUserServiceImpl(SysUserMapper sysUserMapper, ServletUtils servletUtils) {
        this.sysUserMapper = sysUserMapper;
        this.servletUtils = servletUtils;
    }

    /**
     * 查询所有用户信息（非删除标记的用户），分页查询
     *
     * @param pageNum      分页数
     * @param pageSize     分页大小
     * @param searchParams 检索条件（用户名、性别、状态）
     * @return 所有用户
     */
    @Override
    public JSONObject getAllUser(int pageNum, int pageSize, JSONObject searchParams) {
        QueryWrapper queryWrapper = new QueryWrapper();
        JSONObject result = new JSONObject();
        queryWrapper.eq(SysUser::getDelFlag, 0);
        return queryPage(pageNum, pageSize, queryWrapper, result, searchParams);
    }

    /**
     * 获取回收站的用户，分页查询
     *
     * @param pageNum      分页数
     * @param pageSize     分页大小
     * @param searchParams 用户查询条件
     * @return 用户信息
     */
    @Override
    public JSONObject getUserListRecycle(int pageNum, int pageSize, JSONObject searchParams) {
        QueryWrapper queryWrapper = new QueryWrapper();
        JSONObject result = new JSONObject();
        queryWrapper.eq(SysUser::getDelFlag, 1);
        return queryPage(pageNum, pageSize, queryWrapper, result, searchParams);
    }

    /**
     * 校验用户名唯一
     *
     * @param userName 用户名
     * @return true 唯一 false 不唯一
     */
    @Override
    public boolean checkUserNameUnique(String userName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(SysUser::getUserName, userName);
        return sysUserMapper.selectCountByQuery(queryWrapper) == 0;
    }

    /**
     * 添加用户
     *
     * @param sysUser 用户信息
     * @return 结果
     */
    @Override
    public boolean addUser(SysUser sysUser) throws Exception {
        // 新增用户的时候设置默认密码和密码盐
        String salt = PasswordUtils.generateSalt();
        sysUser.setSalt(salt);
        // 默认初始密码123123
        String encrypted = PasswordUtils.encrypt(sysUser.getUsername(), "123456", salt);
        sysUser.setPassword(encrypted);
        // 默认状态为正常（非冻结用户）
        sysUser.setStatus(CommonConstant.USER_UNFREEZE);
        return sysUserMapper.insert(sysUser) > 0;
    }

    /**
     * 更新用户信息
     *
     * @param sysUser 用户信息
     * @return 结果
     */
    @Override
    public boolean updateUser(SysUser sysUser) {
        return sysUserMapper.update(sysUser) > 0;
    }

    /**
     * 批量删除用户信息（假删除-放入回收站）
     *
     * @param userIds 用户ID集合
     * @return true｜false
     */
    @Override
    public boolean logicDeleteBatchUser(List<String> userIds) {
        // 更新用户的del_flag = 1
        if (userIds == null || userIds.isEmpty()) {
            return true;
        }
        // 采用flex的业务逻辑删除
        int i = sysUserMapper.deleteBatchByIds(userIds);
        return i > 0;
    }

    /**
     * 批量删除用户（物理删除）
     *
     * @param userIds 用户ID
     * @return 结果
     */
    @Override
    public boolean physicalDeleteBatchUsers(List<String> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            return true;
        }
        // 采用flex的物理删除
        int i = LogicDeleteManager.execWithoutLogicDelete(() -> sysUserMapper.deleteBatchByIds(userIds));
        return i > 0;
    }

    /**
     * 从回收站批量恢复用户
     *
     * @param userIds 用户ID
     * @return 恢复结果
     */
    @Override
    public boolean recoverFromRecycle(List<String> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            return true;
        }
        SysOpr sysOpr = servletUtils.getSysOpr();
        List<SysUser> sysUsers = userIds.stream().map(userId -> {
            SysUser sysUser = UpdateEntity.of(SysUser.class, userId);
            sysUser.setDelFlag(CommonConstant.DEL_FLAG_0);
            sysUser.setUpdateBy(sysOpr.getUserId());
            sysUser.setUpdateTime(LocalDateTime.now());
            return sysUser;
        }).toList();
        // 调用批量更新
        int i = Db.updateEntitiesBatch(sysUsers);
        return i > 0;
    }

    /**
     * 批量锁定用户
     *
     * @param userIds 用户ID
     * @return 锁定结果
     */
    @Override
    public boolean lockBatchUser(List<String> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            return true;
        }
        SysOpr sysOpr = servletUtils.getSysOpr();
        List<SysUser> sysUsers = userIds.stream().map(userId -> {
            SysUser sysUser = UpdateEntity.of(SysUser.class, userId);
            sysUser.setStatus(CommonConstant.USER_FREEZE);
            sysUser.setUpdateBy(sysOpr.getUserId());
            sysUser.setUpdateTime(LocalDateTime.now());
            return sysUser;
        }).toList();
        // 调用批量更新
        int i = Db.updateEntitiesBatch(sysUsers);
        return i > 0;
    }

    /**
     * 批量解锁用户
     *
     * @param userIds 用户ID
     * @return true ｜ false
     */
    @Override
    public boolean unlockBatchUser(List<String> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            return true;
        }
        SysOpr sysOpr = servletUtils.getSysOpr();
        List<SysUser> sysUsers = userIds.stream().map(userId -> {
            SysUser sysUser = UpdateEntity.of(SysUser.class, userId);
            sysUser.setStatus(CommonConstant.USER_UNFREEZE);
            sysUser.setUpdateBy(sysOpr.getUserId());
            sysUser.setUpdateTime(LocalDateTime.now());
            return sysUser;
        }).toList();
        // 调用批量更新
        int i = Db.updateEntitiesBatch(sysUsers);
        return i > 0;
    }

    /**
     * 重置用户密码(重置为123456)
     *
     * @param userId 用户ID
     * @return true | false
     */
    @Override
    public boolean resetPwd(String userId) {
        // 从数据库查询用户信息（需要用户名）
        // 需要新的密码盐值
        String salt = PasswordUtils.generateSalt();

        return false;
    }

    /**
     * 修改用户密码
     *
     * @param userId 用户ID
     * @param newPwd 新密码
     * @return true| false
     */
    @Override
    public boolean modifyPwd(String userId, String newPwd) {
        return false;
    }

    /**
     * 分页查询
     *
     * @param pageNum      页码
     * @param pageSize     页大小
     * @param queryWrapper 查询条件
     * @param result       结果
     * @return 分页数据
     */
    private JSONObject queryPage(int pageNum, int pageSize, QueryWrapper queryWrapper, JSONObject result, JSONObject searchParams) {
        // 拼接其他的查询条件
        queryWrapper.like(SysUser::getUsername, searchParams.getString("username"), StringUtils.isNotBlank(searchParams.getString("username")));
        queryWrapper.eq(SysUser::getStatus, searchParams.getIntValue("status"), StringUtils.isNotBlank(searchParams.getString("status")));
        queryWrapper.eq(SysUser::getSex, searchParams.getIntValue("sex"), StringUtils.isNotBlank(searchParams.getString("sex")));
        Page<SysUser> paginate;
        boolean isFirstPage = pageNum == 1;
        // flex totalRow参数，传入小于0的会查询总量， 否则不会查询总量
        paginate = LogicDeleteManager.execWithoutLogicDelete(() -> sysUserMapper.paginate(pageNum, pageSize, isFirstPage ? -1 : 0, queryWrapper));
        // 第一页才返回查询的数据总量
        if (isFirstPage) {
            result.put("total", paginate.getTotalRow());
        }

        result.put("data", paginate.getRecords());
        return result;
    }
}
