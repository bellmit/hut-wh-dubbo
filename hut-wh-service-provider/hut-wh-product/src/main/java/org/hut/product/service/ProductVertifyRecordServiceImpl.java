package org.hut.product.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.hut.common.entity.pms.ProductVertifyRecord;
import org.hut.product.mapper.ProductVertifyRecordMapper;
import org.hut.openapi.user.service.pms.IProductVertifyRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 商品审核记录 服务实现类
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
public class ProductVertifyRecordServiceImpl extends ServiceImpl<ProductVertifyRecordMapper, ProductVertifyRecord> implements IProductVertifyRecordService {

}
