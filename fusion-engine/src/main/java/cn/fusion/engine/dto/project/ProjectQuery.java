package cn.fusion.engine.dto.project;

import lombok.Data;

/**
 * @ClassName ProjectQuery
 * @Description 项目查询条件
 * 项目查询条件，用于封装查询项目的条件
 * @Author ycl
 * @Date 2025/5/10 11:18
 * @Version 1.0
 **/
@Data
public class ProjectQuery {

    private String name;

    private Integer type;

    private Integer pageNum;

    private Integer pageSize;

    private Boolean isMine;

    private String tagIDs;
}
