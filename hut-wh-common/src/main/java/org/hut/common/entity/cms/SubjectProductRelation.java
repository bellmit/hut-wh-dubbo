package org.hut.common.entity.cms;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 专题商品关系表
 * </p>
 *
 * @author hutwanghui123
 * @since 2018-12-17
 */
@TableName("cms_subject_product_relation")
public class SubjectProductRelation extends Model<SubjectProductRelation> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("subject_id")
    private Long subjectId;
    @TableField("product_id")
    private Long productId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SubjectProductRelation{" +
        ", id=" + id +
        ", subjectId=" + subjectId +
        ", productId=" + productId +
        "}";
    }
}
