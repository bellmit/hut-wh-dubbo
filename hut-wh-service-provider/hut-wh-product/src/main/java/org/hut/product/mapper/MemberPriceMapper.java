package org.hut.product.mapper;

import org.apache.ibatis.annotations.Param;
import org.hut.common.entity.pms.MemberPrice;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商品会员价格表 Mapper 接口
 * </p>
 *
 * @author hutwanghui123
 * @since 2018-12-16
 */
public interface MemberPriceMapper extends BaseMapper<MemberPrice> {
    int insertList(@Param("list") List<MemberPrice> memberPriceList);
}
