package org.hut.product.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.hut.common.entity.pms.ProductCategoryAttributeRelation;
import org.hut.product.mapper.ProductCategoryAttributeRelationMapper;
import org.hut.openapi.user.service.pms.IProductCategoryAttributeRelationService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类） 服务实现类
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
public class ProductCategoryAttributeRelationServiceImpl extends ServiceImpl<ProductCategoryAttributeRelationMapper, ProductCategoryAttributeRelation> implements IProductCategoryAttributeRelationService {

}
