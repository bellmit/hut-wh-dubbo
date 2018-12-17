package org.hut.product.mapper;

import org.apache.ibatis.annotations.Param;
import org.hut.common.entity.pms.ProductCategory;
import org.hut.common.entity.pms.ProductLadder;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 产品阶梯价格表(只针对同商品) Mapper 接口
 * </p>
 *
 * @author hutwanghui123
 * @since 2018-12-16
 */
public interface ProductLadderMapper extends BaseMapper<ProductLadder> {
    int insertList(@Param("list") List<ProductLadder> productLadderList);
}
