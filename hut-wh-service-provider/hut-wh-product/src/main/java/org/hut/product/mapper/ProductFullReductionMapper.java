package org.hut.product.mapper;

import org.apache.ibatis.annotations.Param;
import org.hut.common.entity.pms.ProductFullReduction;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 产品满减表(只针对同商品) Mapper 接口
 * </p>
 *
 * @author hutwanghui123
 * @since 2018-12-16
 */
public interface ProductFullReductionMapper extends BaseMapper<ProductFullReduction> {
    int insertList(@Param("list") List<ProductFullReduction> productFullReductionList);
}
