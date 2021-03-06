package org.hut.product.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.hut.common.entity.pms.ProductAttributeValue;
import org.hut.product.mapper.ProductAttributeValueMapper;
import org.hut.openapi.user.service.pms.IProductAttributeValueService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 存储产品参数信息的表 服务实现类
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
        group = "pms"
)
public class ProductAttributeValueServiceImpl extends ServiceImpl<ProductAttributeValueMapper, ProductAttributeValue> implements IProductAttributeValueService {

}
