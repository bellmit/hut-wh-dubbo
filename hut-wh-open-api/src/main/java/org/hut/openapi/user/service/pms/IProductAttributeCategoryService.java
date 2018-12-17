package org.hut.openapi.user.service.pms;

import org.hut.common.entity.pms.ProductAttributeCategory;
import com.baomidou.mybatisplus.service.IService;
import org.hut.common.utils.PageUtils;

import java.util.Map;

/**
 * <p>
 * 产品属性分类表 服务类
 * </p>
 *
 * @author hutwanghui123
 * @since 2018-12-16
 */
public interface IProductAttributeCategoryService extends IService<ProductAttributeCategory> {
    public PageUtils queryPage(Map<String, Object> params);
}
