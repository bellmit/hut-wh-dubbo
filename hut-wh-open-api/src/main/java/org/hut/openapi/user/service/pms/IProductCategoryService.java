package org.hut.openapi.user.service.pms;

import org.hut.common.dto.ProductCategoryParam;
import org.hut.common.entity.pms.ProductCategory;
import com.baomidou.mybatisplus.service.IService;
import org.hut.common.entity.pms.ProductCategoryWithChildrenItem;
import org.hut.common.utils.PageUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author hutwanghui123
 * @since 2018-12-16
 */
public interface IProductCategoryService extends IService<ProductCategory> {

    @Transactional
    public boolean insertProductCategoryParam(ProductCategoryParam productCategoryParam);

    @Transactional
    public boolean updateProductCategoryParam(Long id, ProductCategoryParam pmsProductCategoryParam);


    @Transactional
    public boolean updateNavStatusBeatch(List<Long> ids, Integer navStatus);

    @Transactional
    public boolean updateShowStatusBeatch(List<Long> ids, Integer showStatus);

    PageUtils queryPage(Map<String, Object> params);

    List<ProductCategoryWithChildrenItem> listWithChildren();
}
