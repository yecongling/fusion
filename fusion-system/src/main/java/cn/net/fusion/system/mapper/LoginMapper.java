package cn.net.fusion.system.mapper;

import cn.net.fusion.system.entity.SysUser;

/**
 * @ClassName LoginMapper
 * @Description 登录相关的mapper
 * @Author 叶丛林
 * @Date 2024/11/4 21:09
 * @Version 1.0
 **/
public interface LoginMapper {

    /**
     * 根据用户名查询用户信息，用于登录监测
     *
     * @param username 用户名
     * @return 用户信息
     */
    SysUser getUserByName(String username);
}
