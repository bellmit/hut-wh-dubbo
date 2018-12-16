package org.hut.common.entity.pms;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 产品属性分类表
 * </p>
 *
 * @author hutwanghui123
 * @since 2018-12-16
 */
@TableName("pms_product_attribute_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductAttributeCategory extends Model<ProductAttributeCategory> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    /**
     * 属性数量
     */
    @TableField("attribute_count")
    private Integer attributeCount;
    /**
     * 参数数量
     */
    @TableField("param_count")
    private Integer paramCount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAttributeCount() {
        return attributeCount;
    }

    public void setAttributeCount(Integer attributeCount) {
        this.attributeCount = attributeCount;
    }

    public Integer getParamCount() {
        return paramCount;
    }

    public void setParamCount(Integer paramCount) {
        this.paramCount = paramCount;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ProductAttributeCategory{" +
        ", id=" + id +
        ", name=" + name +
        ", attributeCount=" + attributeCount +
        ", paramCount=" + paramCount +
        "}";
    }
}
