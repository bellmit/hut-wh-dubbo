package org.hut.product.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.hut.common.entity.pms.Brand;
import org.hut.product.mapper.BrandMapper;
import org.hut.openapi.user.service.pms.IBrandService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 品牌表 服务实现类
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
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

}
