package org.hut.product.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang.StringUtils;
import org.hut.common.entity.pms.Brand;
import org.hut.common.utils.PageUtils;
import org.hut.common.utils.Query;
import org.hut.product.mapper.BrandMapper;
import org.hut.openapi.user.service.pms.IBrandService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

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
        group = "pms"
)
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String tag = (String) params.get("name");
        Page<Brand> page = this.selectPage(
                new Query<Brand>(params).getPage(),
                new EntityWrapper<Brand>()
                        .like(StringUtils.isNotBlank(tag), "name", tag)
                        .orderBy("id", true)
        );
        return new PageUtils(page);
    }

    @Override
    public boolean updateBrandStatusBatchIds(List<Long> ids, Integer showStatus, String statusType) {
        List<Brand> changeList = this.selectList(new EntityWrapper<Brand>().in("id", ids));
        changeList.stream().forEach(b -> {
            if (statusType.equals("brand")) {
                b.setShowStatus(showStatus);
            } else {
                b.setFactoryStatus(showStatus);
            }
        });
        if (this.updateBatchById(changeList)) {
            return true;
        } else {
            return false;
        }
    }
}
