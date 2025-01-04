package cn.net.fusion.system.service.impl;

import cn.net.fusion.system.entity.SysRole;
import cn.net.fusion.system.mapper.SysRoleMapper;
import cn.net.fusion.system.service.ISysRoleService;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SysRoleServiceImpl
 * @Description 系统角色service实现
 * @Author ycl
 * @Date 2025/1/4 10:05
 * @Version 1.0
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    private final SysRoleMapper sysRoleMapper;
    @Autowired
    public SysRoleServiceImpl(SysRoleMapper sysRoleMapper) {
        this.sysRoleMapper = sysRoleMapper;
    }

    /**
     * 查询所有角色
     *
     * @param sysRole 系统角色
     * @return 符合条件的系统角色
     */
    @Override
    public List<SysRole> selectRoleList(SysRole sysRole) {
        QueryWrapper queryWrapper = new QueryWrapper();
        // 根据角色名称、角色编码、状态进行查询
        queryWrapper.like(SysRole::getRoleName, sysRole.getRoleName());
        queryWrapper.like(SysRole::getRoleCode, sysRole.getRoleCode());
        queryWrapper.eq(SysRole::getStatus, sysRole.getStatus());
        // 选择需要的字段
        queryWrapper.select(SysRole::getId, SysRole::getRoleName, SysRole::getRoleCode, SysRole::getRoleName, SysRole::getStatus, SysRole::getRoleType, SysRole::getRemark);
        return sysRoleMapper.selectListByQuery(queryWrapper);
    }

    /**
     * 插入角色
     *
     * @param sysRole 角色数据
     * @return 受影响的行数
     */
    @Override
    public Integer insertRole(SysRole sysRole) {
        return sysRoleMapper.insert(sysRole);
    }
}
