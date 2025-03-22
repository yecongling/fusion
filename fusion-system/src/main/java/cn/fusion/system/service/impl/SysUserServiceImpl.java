package cn.fusion.system.service.impl;

import cn.fusion.framework.constant.CommonConstant;
import cn.fusion.framework.core.SysOpr;
import cn.fusion.framework.exception.BusinessException;
import cn.fusion.framework.utils.PasswordUtils;
import cn.fusion.framework.utils.ServletUtils;
import cn.fusion.system.entity.SysUser;
import cn.fusion.system.mapper.SysUserMapper;
import cn.fusion.system.service.ISysUserService;
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
     * @param searchParams 检索条件（用户名、性别、状态）
     * @return 所有用户
     */
    @Override
    public JSONObject getAllUser(JSONObject searchParams) {
        QueryWrapper queryWrapper = new QueryWrapper();
        JSONObject result = new JSONObject();
        // 获取分页参数
        int pageNum = searchParams.getIntValue("pageNum");
        int pageSize = searchParams.getIntValue("pageSize");
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
    public boolean logicDeleteBatchUser(List<Long> userIds) {
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
    public boolean physicalDeleteBatchUsers(List<Long> userIds) {
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
    public boolean recoverFromRecycle(List<Long> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            return true;
        }
        SysOpr sysOpr = servletUtils.getSysOpr();
        List<SysUser> sysUsers = userIds.stream().map(userId -> {
            SysUser sysUser = UpdateEntity.of(SysUser.class, userId);
            sysUser.setDelFlag(Boolean.FALSE);
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
    public boolean lockBatchUser(List<Long> userIds) {
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
    public boolean unlockBatchUser(List<Long> userIds) {
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
    public boolean resetPwd(Long userId) throws Exception {
        // 从数据库查询用户信息（需要用户名）
        // 需要新的密码盐值
        String salt = PasswordUtils.generateSalt();
        SysUser sysUser = sysUserMapper.selectOneById(userId);
        if (sysUser == null) {
            throw new BusinessException("用户不存在，无法进行密码重置！");
        }
        String encrypted = PasswordUtils.encrypt(sysUser.getUsername(), "123456", salt);
        // 这么写是为了只更新其中调用了setter的字段
        return extracted(userId, encrypted, salt);
    }

    /**
     * 提取公共方法
     *
     * @param userId      用户ID
     * @param encrypted   加密后的密码
     * @param salt        密码盐值
     * @return true | false
     */
    private boolean extracted(Long userId, String encrypted, String salt) {
        SysUser user = UpdateEntity.of(SysUser.class);
        user.setPassword(encrypted);
        user.setSalt(salt);
        user.setId(userId);
        // 更新操作时间
        user.setUpdateTime(LocalDateTime.now());
        // 更新操作人
        user.setUpdateBy(servletUtils.getSysOpr().getUserId());
        int update = sysUserMapper.update(user);
        return update > 0;
    }

    /**
     * 修改用户密码
     *
     * @param userId 用户ID
     * @param newPwd 新密码
     * @return true| false
     */
    @Override
    public boolean modifyPwd(Long userId, String newPwd) throws Exception {
        // 从数据库查询用户信息（需要用户名）
        // 需要新的密码盐值
        SysUser sysUser = sysUserMapper.selectOneById(userId);
        if (sysUser == null) {
            throw new BusinessException("用户不存在，无法进行密码修改！");
        }
        String salt = PasswordUtils.generateSalt();
        String encrypted = PasswordUtils.encrypt(sysUser.getUsername(), newPwd, salt);
        return extracted(userId, encrypted, salt);
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
        // 处理searchParams为null的情况
        if (searchParams == null) {
            searchParams = new JSONObject();
        }
        // 拼接其他的查询条件
        queryWrapper.like(SysUser::getUsername, searchParams.getString("username"), StringUtils.isNotBlank(searchParams.getString("username")));
        queryWrapper.eq(SysUser::getStatus, searchParams.getIntValue("status"), StringUtils.isNotBlank(searchParams.getString("status")));
        queryWrapper.eq(SysUser::getSex, searchParams.getIntValue("sex"), StringUtils.isNotBlank(searchParams.getString("sex")));
        return getPageData(pageNum, pageSize, queryWrapper, result);
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
    private JSONObject getPageData(int pageNum, int pageSize, QueryWrapper queryWrapper, JSONObject result) {
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
