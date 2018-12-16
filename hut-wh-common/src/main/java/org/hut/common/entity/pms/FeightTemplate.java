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
 * 运费模版
 * </p>
 *
 * @author hutwanghui123
 * @since 2018-12-16
 */
@TableName("pms_feight_template")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FeightTemplate extends Model<FeightTemplate> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    /**
     * 计费类型:0->按重量；1->按件数
     */
    @TableField("charge_type")
    private Integer chargeType;
    /**
     * 首重kg
     */
    @TableField("first_weight")
    private BigDecimal firstWeight;
    /**
     * 首费（元）
     */
    @TableField("first_fee")
    private BigDecimal firstFee;
    @TableField("continue_weight")
    private BigDecimal continueWeight;
    @TableField("continme_fee")
    private BigDecimal continmeFee;
    /**
     * 目的地（省、市）
     */
    private String dest;


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

    public Integer getChargeType() {
        return chargeType;
    }

    public void setChargeType(Integer chargeType) {
        this.chargeType = chargeType;
    }

    public BigDecimal getFirstWeight() {
        return firstWeight;
    }

    public void setFirstWeight(BigDecimal firstWeight) {
        this.firstWeight = firstWeight;
    }

    public BigDecimal getFirstFee() {
        return firstFee;
    }

    public void setFirstFee(BigDecimal firstFee) {
        this.firstFee = firstFee;
    }

    public BigDecimal getContinueWeight() {
        return continueWeight;
    }

    public void setContinueWeight(BigDecimal continueWeight) {
        this.continueWeight = continueWeight;
    }

    public BigDecimal getContinmeFee() {
        return continmeFee;
    }

    public void setContinmeFee(BigDecimal continmeFee) {
        this.continmeFee = continmeFee;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FeightTemplate{" +
        ", id=" + id +
        ", name=" + name +
        ", chargeType=" + chargeType +
        ", firstWeight=" + firstWeight +
        ", firstFee=" + firstFee +
        ", continueWeight=" + continueWeight +
        ", continmeFee=" + continmeFee +
        ", dest=" + dest +
        "}";
    }
}
