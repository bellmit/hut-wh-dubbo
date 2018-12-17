package org.hut.product.mapper;

import org.apache.ibatis.annotations.Param;
import org.hut.common.entity.pms.ProductCategory;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.hut.common.entity.pms.ProductCategoryWithChildrenItem;

import java.util.List;

/**
 * <p>
 * 产品分类 Mapper 接口
 * </p>
 *
 * @author hutwanghui123
 * @since 2018-12-16
 */
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {
    List<ProductCategoryWithChildrenItem> listWithChildren();
    int insertList(@Param("list") List<ProductCategory> productCategoryList);
}
