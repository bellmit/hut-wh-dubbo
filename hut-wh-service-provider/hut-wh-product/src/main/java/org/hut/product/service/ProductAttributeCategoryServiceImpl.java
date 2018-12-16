package org.hut.product.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.hut.common.entity.pms.ProductAttributeCategory;
import org.hut.product.mapper.ProductAttributeCategoryMapper;
import org.hut.openapi.user.service.pms.IProductAttributeCategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 产品属性分类表 服务实现类
 * </p>
 *
 * @author hutwanghui123
 * @since 2018-12-16
 */
@Service(
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}",
        version = "1.0.0",
        group = "sys"
)
public class ProductAttributeCategoryServiceImpl extends ServiceImpl<ProductAttributeCategoryMapper, ProductAttributeCategory> implements IProductAttributeCategoryService {

}
