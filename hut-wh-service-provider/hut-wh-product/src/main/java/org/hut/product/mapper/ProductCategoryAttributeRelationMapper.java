package org.hut.product.mapper;

import org.apache.ibatis.annotations.Param;
import org.hut.common.entity.pms.ProductCategoryAttributeRelation;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类） Mapper 接口
 * </p>
 *
 * @author hutwanghui123
 * @since 2018-12-16
 */
public interface ProductCategoryAttributeRelationMapper extends BaseMapper<ProductCategoryAttributeRelation> {
    int insertList(@Param("list") List<ProductCategoryAttributeRelation> productCategoryAttributeRelationList);

    int updateList(@Param("list") List<ProductCategoryAttributeRelation> relationList);
}

