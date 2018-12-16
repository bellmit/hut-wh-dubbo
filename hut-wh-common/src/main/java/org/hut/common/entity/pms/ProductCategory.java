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
 * 产品分类
 * </p>
 *
 * @author hutwanghui123
 * @since 2018-12-16
 */
@TableName("pms_product_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductCategory extends Model<ProductCategory> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 上机分类的编号：0表示一级分类
     */
    @TableField("parent_id")
    private Long parentId;
    private String name;
    /**
     * 分类级别：0->1级；1->2级
     */
    private Integer level;
    @TableField("product_count")
    private Integer productCount;
    @TableField("product_unit")
    private String productUnit;
    /**
     * 是否显示在导航栏：0->不显示；1->显示
     */
    @TableField("nav_status")
    private Integer navStatus;
    /**
     * 显示状态：0->不显示；1->显示
     */
    @TableField("show_status")
    private Integer showStatus;
    private Integer sort;
    /**
     * 图标
     */
    private String icon;
    private String keywords;
    /**
     * 描述
     */
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public Integer getNavStatus() {
        return navStatus;
    }

    public void setNavStatus(Integer navStatus) {
        this.navStatus = navStatus;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
        ", id=" + id +
        ", parentId=" + parentId +
        ", name=" + name +
        ", level=" + level +
        ", productCount=" + productCount +
        ", productUnit=" + productUnit +
        ", navStatus=" + navStatus +
        ", showStatus=" + showStatus +
        ", sort=" + sort +
        ", icon=" + icon +
        ", keywords=" + keywords +
        ", description=" + description +
        "}";
    }
}
