package org.hut.openapi.user.service.pms;

import org.hut.common.dto.ProductAttrInfo;
import org.hut.common.entity.pms.ProductAttribute;
import com.baomidou.mybatisplus.service.IService;
import org.hut.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品属性参数表 服务类
 * </p>
 *
 * @author hutwanghui123
 * @since 2018-12-16
 */
public interface IProductAttributeService extends IService<ProductAttribute> {
    PageUtils queryPage(Map<String, Object> params);
    List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId);
}
