package org.hut.product.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang.StringUtils;
import org.hut.common.entity.pms.Brand;
import org.hut.common.entity.pms.ProductAttributeCategory;
import org.hut.common.utils.PageUtils;
import org.hut.common.utils.Query;
import org.hut.product.mapper.ProductAttributeCategoryMapper;
import org.hut.openapi.user.service.pms.IProductAttributeCategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.Map;

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
        group = "pms"
)
public class ProductAttributeCategoryServiceImpl extends ServiceImpl<ProductAttributeCategoryMapper, ProductAttributeCategory> implements IProductAttributeCategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String tag = (String) params.get("name");
        Page<ProductAttributeCategory> page = this.selectPage(
                new Query<ProductAttributeCategory>(params).getPage(),
                new EntityWrapper<ProductAttributeCategory>()
                        .like(StringUtils.isNotBlank(tag), "name", tag)
                        .orderBy("id", true)
        );
        return new PageUtils(page);
    }
}
