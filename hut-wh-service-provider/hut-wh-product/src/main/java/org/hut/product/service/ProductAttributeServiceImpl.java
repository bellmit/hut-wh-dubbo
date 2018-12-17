package org.hut.product.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang.StringUtils;
import org.hut.common.dto.ProductAttrInfo;
import org.hut.common.entity.pms.ProductAttribute;
import org.hut.common.utils.PageUtils;
import org.hut.common.utils.Query;
import org.hut.product.mapper.ProductAttributeMapper;
import org.hut.openapi.user.service.pms.IProductAttributeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品属性参数表 服务实现类
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
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttribute> implements IProductAttributeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String tag = (String) params.get("name");
        Page<ProductAttribute> page = this.selectPage(
                new Query<ProductAttribute>(params).getPage(),
                new EntityWrapper<ProductAttribute>()
                        .like(StringUtils.isNotBlank(tag), "name", tag)
                        .orderBy("id", true)
        );
        return new PageUtils(page);
    }

    @Override
    public List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId) {
        return this.baseMapper.getProductAttrInfoByPcId(productCategoryId);
    }
}
