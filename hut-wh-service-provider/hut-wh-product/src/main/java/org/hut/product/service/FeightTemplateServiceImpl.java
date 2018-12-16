package org.hut.product.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.hut.common.entity.pms.FeightTemplate;
import org.hut.product.mapper.FeightTemplateMapper;
import org.hut.openapi.user.service.pms.IFeightTemplateService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 运费模版 服务实现类
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
public class FeightTemplateServiceImpl extends ServiceImpl<FeightTemplateMapper, FeightTemplate> implements IFeightTemplateService {

}
