package org.hut.product.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.lang.StringUtils;
import org.hut.common.entity.pms.SkuStock;
import org.hut.openapi.user.service.pms.ISkuStockService;
import org.hut.product.mapper.SkuStockMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * sku的库存 服务实现类
 * </p>
 *
 * @author hutwanghui
 * @since 2018-12-17
 */
@Service(
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}",
        version = "1.0.0",
        group = "pms"
)
public class SkuStockServiceImpl extends ServiceImpl<SkuStockMapper, SkuStock> implements ISkuStockService {

    @Override
    public List<SkuStock> selectByProductIdAndKeyWord(Long pid, String keyword) {
        return this.selectList(new EntityWrapper<SkuStock>()
                .like(StringUtils.isNotBlank(keyword), "keyword", keyword)
                .eq("product_id", pid)
        );
    }

    @Override
    public boolean updateBatchByProductId(Long pid, List<SkuStock> skuStockList) {
        try {
            List<SkuStock> listWithPid = skuStockList
                    .stream()
                    .filter(skuStock ->
                            skuStock.getProductId().equals(pid))
                    .collect(Collectors.toList());
            return this.updateBatchById(listWithPid);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("===========发生事务回滚===========");
            e.printStackTrace();
        }
        return false;
    }
}
