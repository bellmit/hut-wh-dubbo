package org.hut.openapi.user.service.pms;

import org.hut.common.entity.pms.Product;
import com.baomidou.mybatisplus.service.IService;
import org.hut.common.entity.pms.ProductParam;
import org.hut.common.entity.pms.ProductQueryParam;
import org.hut.common.utils.PageUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author hutwanghui123
 * @since 2018-12-16
 */
public interface IProductService extends IService<Product> {
    @Transactional
    public boolean insertProductWithOtherList(ProductParam productParam);

    @Transactional
    public boolean updateProductWithOtherList(Long id, ProductParam productParam);

    PageUtils queryPage(Map<String, Object> params);

    int updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail);

    int updatePublishStatus(List<Long> ids, Integer publishStatus);

    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    int updateNewStatus(List<Long> ids, Integer newStatus);

    int updateDeleteStatus(List<Long> ids, Integer deleteStatus);
}
