package cn.fusion.system.controller;

import cn.fusion.system.service.ISysDictService;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SysDictController
 * @Description 数据字典
 * @Author ycl
 * @Date 2025/3/27 13:36
 * @Version 1.0
 **/
@RestController
@RequestMapping("/system/dict")
public class SysDictController {

    private final ISysDictService sysDictService;

    @Autowired
    public SysDictController(ISysDictService sysDictService) {
        this.sysDictService = sysDictService;
    }

    /**
     * 查询字典列表
     *
     * @param searchParams 检索参数，包括分页参数
     * @return 字典列表+总数据量
     */
    @PostMapping("/getDictList")
    JSONObject selectDictList(@RequestBody JSONObject searchParams) {
        return sysDictService.selectDictList(searchParams.getIntValue("pageNum"), searchParams.getIntValue("pageSize"), searchParams);
    }
}
