package cn.net.fusion.engine.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @ClassName EndpointConfigTypeVO
 * @Description 端点配置和类型的联合实体对象，用于页面上的左边那颗树结构
 * @Author ycl
 * @Date 2024/11/26 09:39
 * @Version 1.0
 */
public class EndpointConfigTypeVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 3577463857341920571L;
    // 分类id
    private Integer categoryId;
    // 分类名称
    private String categoryName;

    // 端点类型id
    private Integer typeId;
    // 端点类型名
    private String typeName;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
