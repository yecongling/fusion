package cn.fusion.system.service.impl;

import cn.fusion.system.entity.SysDict;
import cn.fusion.system.entity.SysRole;
import cn.fusion.system.mapper.SysDictMapper;
import cn.fusion.system.service.ISysDictService;
import com.alibaba.fastjson2.JSONObject;
import com.mybatisflex.core.logicdelete.LogicDeleteManager;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryMethods;
import com.mybatisflex.core.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SysDictServiceImpl
 * @Description 字典服务具体实现
 * @Author 叶丛林
 * @Date 2025/3/31 20:36
 * @Version 1.0
 */
@Service
public class SysDictServiceImpl implements ISysDictService {

    private final SysDictMapper sysDictMapper;

    @Autowired
    public SysDictServiceImpl(SysDictMapper sysDictMapper) {
        this.sysDictMapper = sysDictMapper;
    }

    /**
     * 分页查询字典数据
     *
     * @param pageNum      页码
     * @param pageSize     分页大小
     * @param searchParams 检索参数
     * @return 字典列表
     */
    @Override
    public JSONObject selectDictList(int pageNum, int pageSize, JSONObject searchParams) {
        // 查询需要的字段
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select(
                QueryMethods.column(SysDict::getId),
                QueryMethods.column(SysDict::getDictCode),
                QueryMethods.column(SysDict::getDictName),
                QueryMethods.column(SysDict::getType),
                QueryMethods.column(SysDict::getDelFlag),
                QueryMethods.column(SysDict::getCreateTime)
        );

        // 需要返回到前端的内容
        JSONObject result = new JSONObject();
        return queryDictPage(pageNum, pageSize, queryWrapper, result, searchParams);
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
    private JSONObject queryDictPage(int pageNum, int pageSize, QueryWrapper queryWrapper, JSONObject result, JSONObject searchParams) {
        // 处理searchParams为null的情况
        if (searchParams == null) {
            searchParams = new JSONObject();
        }
        // 拼接其他的查询条件
        queryWrapper.like(SysDict::getDictCode, searchParams.getString("dictCode"), StringUtils.isNotBlank(searchParams.getString("dictCode")));
        queryWrapper.eq(SysDict::getDictName, searchParams.getIntValue("dictName"), StringUtils.isNotBlank(searchParams.getString("dictName")));
        queryWrapper.eq(SysDict::getDelFlag, searchParams.getBooleanValue("delFlag"), StringUtils.isNotBlank(searchParams.getString("delFlag")));
        return getRolePageData(pageNum, pageSize, queryWrapper, result);
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
    private JSONObject getRolePageData(int pageNum, int pageSize, QueryWrapper queryWrapper, JSONObject result) {
        Page<SysDict> paginate;
        boolean isFirstPage = pageNum == 1;
        // flex totalRow参数，传入小于0的会查询总量， 否则不会查询总量
        paginate = LogicDeleteManager.execWithoutLogicDelete(() -> sysDictMapper.paginate(pageNum, pageSize, isFirstPage ? -1 : 0, queryWrapper));
        // 第一页才返回查询的数据总量
        if (isFirstPage) {
            result.put("total", paginate.getTotalRow());
        }

        result.put("data", paginate.getRecords());
        return result;
    }
}
