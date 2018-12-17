package org.hut.product.mapper;

import org.apache.ibatis.annotations.Param;
import org.hut.common.entity.pms.Product;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商品信息 Mapper 接口
 * </p>
 *
 * @author hutwanghui123
 * @since 2018-12-16
 */
public interface ProductMapper extends BaseMapper<Product> {
    public List<Product> selectByProductCategoryId(@Param("pcid") Long pcid);
}
