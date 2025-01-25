package cn.net.fusion.system.mapper;

import cn.net.fusion.system.entity.SysUser;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName LoginMapper
 * @Description 登录相关的mapper
 * @Author 叶丛林
 * @Date 2024/11/4 21:09
 * @Version 1.0
 **/
@Mapper
public interface LoginMapper extends BaseMapper<SysUser> {
}
