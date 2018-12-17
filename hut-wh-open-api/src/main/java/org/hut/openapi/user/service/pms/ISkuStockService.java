package org.hut.openapi.user.service.pms;

import org.hut.common.entity.pms.SkuStock;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * sku的库存 服务类
 * </p>
 *
 * @author hutwanghui
 * @since 2018-12-17
 */
public interface ISkuStockService extends IService<SkuStock> {

    List<SkuStock> selectByProductIdAndKeyWord(Long pid, String keyword);

    @Transactional
    boolean updateBatchByProductId(Long pid, List<SkuStock> skuStockList);
}
