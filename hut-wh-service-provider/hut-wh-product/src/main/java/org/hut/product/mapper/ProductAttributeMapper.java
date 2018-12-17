package org.hut.product.mapper;

import org.apache.ibatis.annotations.Param;
import org.hut.common.dto.ProductAttrInfo;
import org.hut.common.entity.pms.ProductAttribute;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 Mapper 接口
 * </p>
 *
 * @author hutwanghui123
 * @since 2018-12-16
 */
public interface ProductAttributeMapper extends BaseMapper<ProductAttribute> {
    List<ProductAttrInfo> getProductAttrInfoByPcId(@Param("id") Long productCategoryId);
}
