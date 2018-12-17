package org.hut.product.mapper;

import org.apache.ibatis.annotations.Param;
import org.hut.common.entity.pms.MemberPrice;
import org.hut.common.entity.pms.SkuStock;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * sku的库存 Mapper 接口
 * </p>
 *
 * @author hutwanghui
 * @since 2018-12-17
 */
public interface SkuStockMapper extends BaseMapper<SkuStock> {
    int insertList(@Param("list") List<SkuStock> skuStockList);
}
