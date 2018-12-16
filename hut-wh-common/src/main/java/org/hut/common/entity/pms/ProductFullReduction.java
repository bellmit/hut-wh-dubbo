package org.hut.common.entity.pms;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 产品满减表(只针对同商品)
 * </p>
 *
 * @author hutwanghui123
 * @since 2018-12-16
 */
@TableName("pms_product_full_reduction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductFullReduction extends Model<ProductFullReduction> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("product_id")
    private Long productId;
    @TableField("full_price")
    private BigDecimal fullPrice;
    @TableField("reduce_price")
    private BigDecimal reducePrice;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(BigDecimal fullPrice) {
        this.fullPrice = fullPrice;
    }

    public BigDecimal getReducePrice() {
        return reducePrice;
    }

    public void setReducePrice(BigDecimal reducePrice) {
        this.reducePrice = reducePrice;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ProductFullReduction{" +
        ", id=" + id +
        ", productId=" + productId +
        ", fullPrice=" + fullPrice +
        ", reducePrice=" + reducePrice +
        "}";
    }
}
