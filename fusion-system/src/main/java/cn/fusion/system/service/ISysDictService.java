package cn.fusion.system.service;

import cn.fusion.system.entity.SysDict;
import com.alibaba.fastjson2.JSONObject;

import java.util.List;

/**
 * @ClassName ISysDictService
 * @Description 数据字典服务接口
 * @Author 叶丛林
 * @Date 2025/3/31 20:25
 * @Version 1.0
 */
public interface ISysDictService {

    /**
     * 分页查询字典数据
     *
     * @param pageNum      页码
     * @param pageSize     分页大小
     * @param searchParams 检索参数
     * @return 字典列表
     */
    JSONObject selectDictList(int pageNum, int pageSize, JSONObject searchParams);
}
