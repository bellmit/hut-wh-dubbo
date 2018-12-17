package org.hut.common.entity.pms;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hut.common.entity.cms.PrefrenceAreaProductRelation;
import org.hut.common.entity.cms.SubjectProductRelation;

import java.util.List;

/**
 * 创建和修改商品时使用的参数
 * Created by macro on 2018/4/26.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductParam extends Product {
    @ApiModelProperty("商品阶梯价格设置")
    private List<ProductLadder> productLadderList;
    @ApiModelProperty("商品满减价格设置")
    private List<ProductFullReduction> productFullReductionList;
    @ApiModelProperty("商品会员价格设置")
    private List<MemberPrice> memberPriceList;
    @ApiModelProperty("商品的sku库存信息")
    private List<SkuStock> skuStockList;
    @ApiModelProperty("商品参数及自定义规格属性")
    private List<ProductAttributeValue> productAttributeValueList;
//    @ApiModelProperty("专题和商品关系")
//    private List<SubjectProductRelation> subjectProductRelationList;
//    @ApiModelProperty("优选专区和商品的关系")
//    private List<PrefrenceAreaProductRelation> prefrenceAreaProductRelationList;

}
