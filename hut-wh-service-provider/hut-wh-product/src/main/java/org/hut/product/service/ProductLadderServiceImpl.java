package org.hut.product.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.hut.common.entity.pms.ProductLadder;
import org.hut.product.mapper.ProductLadderMapper;
import org.hut.openapi.user.service.pms.IProductLadderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 产品阶梯价格表(只针对同商品) 服务实现类
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
public class ProductLadderServiceImpl extends ServiceImpl<ProductLadderMapper, ProductLadder> implements IProductLadderService {

}
