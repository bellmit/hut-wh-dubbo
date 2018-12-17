package org.hut.openapi.user.service.pms;

import org.hut.common.entity.pms.Brand;
import com.baomidou.mybatisplus.service.IService;
import org.hut.common.utils.PageUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author hutwanghui123
 * @since 2018-12-16
 */
public interface IBrandService extends IService<Brand> {
    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    public PageUtils queryPage(Map<String, Object> params);

    @Transactional
    public boolean updateBrandStatusBatchIds(List<Long> ids,Integer showStatus,String statusType);
}
