package org.hut.product.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.java.Log;
import org.apache.commons.lang.StringUtils;
import org.hut.common.entity.pms.*;
import org.hut.common.utils.PageUtils;
import org.hut.common.utils.Query;
import org.hut.product.mapper.*;
import org.hut.product.mapper.ProductMapper;
import org.hut.openapi.user.service.pms.IProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.jboss.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品信息 服务实现类
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
@Log
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private MemberPriceMapper memberPriceMapper;
    @Autowired
    private ProductLadderMapper productLadderMapper;
    @Autowired
    private ProductFullReductionMapper productFullReductionMapper;
    @Autowired
    private SkuStockMapper skuStockMapper;
    @Autowired
    private ProductAttributeValueMapper productAttributeValueMapper;

    @Override
    public boolean insertProductWithOtherList(ProductParam productParam) {
        try {
            //创建商品,因为Param继承了Product
            Product product = productParam;
            product.setId(null);
            boolean insertResult = this.insert(product);
            //根据促销类型设置价格：、阶梯价格、满减价格
            Long productId = product.getId();
            //会员价格
            relateAndInsertList(memberPriceMapper, productParam.getMemberPriceList(), productId);
            //阶梯价格
            relateAndInsertList(productLadderMapper, productParam.getProductLadderList(), productId);
            //满减价格
            relateAndInsertList(productFullReductionMapper, productParam.getProductFullReductionList(), productId);
            //处理sku的编码
            handleSkuStockCode(productParam.getSkuStockList(), productId);
            //添加sku库存信息
            relateAndInsertList(skuStockMapper, productParam.getSkuStockList(), productId);
            //添加商品参数,添加自定义商品规格
            relateAndInsertList(productAttributeValueMapper, productParam.getProductAttributeValueList(), productId);
            //关联专题
//        relateAndInsertList(subjectProductRelationDao, productParam.getSubjectProductRelationList(), productId);
            //关联优选
//        relateAndInsertList(prefrenceAreaProductRelationDao, productParam.getPrefrenceAreaProductRelationList(), productId);
            return insertResult;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("===========发生事务回滚===========");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateProductWithOtherList(Long id, ProductParam productParam) {
        try {
            Product product = productParam;
            product.setId(id);
            boolean updateResult = this.updateById(product);
            //会员价格
            relateAndInsertList(memberPriceMapper, productParam.getMemberPriceList(), id);
            //阶梯价格
            relateAndInsertList(productLadderMapper, productParam.getProductLadderList(), id);
            //满减价格
            relateAndInsertList(productFullReductionMapper, productParam.getProductFullReductionList(), id);
            //修改sku库存信息
            relateAndInsertList(skuStockMapper, productParam.getSkuStockList(), id);
            //修改商品参数,添加自定义商品规格
            relateAndInsertList(productAttributeValueMapper, productParam.getProductAttributeValueList(), id);
//            //关联专题
//            //关联优选
            return updateResult;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("===========发生事务回滚===========");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String publishStatus = (String) params.get("publishStatus");
        String verifyStatus = (String) params.get("verifyStatus");
        String keyword = (String) params.get("keyword");
        String productSn = (String) params.get("productSn");
        String productCategoryId = (String) params.get("productCategoryId");
        String brandId = (String) params.get("brandId");
        Page<Product> page = this.selectPage(
                new Query<Product>(params).getPage(),
                new EntityWrapper<Product>()
                        .like(StringUtils.isNotBlank(publishStatus), "publishStatus", publishStatus)
                        .like(StringUtils.isNotBlank(verifyStatus), "verifyStatus", verifyStatus)
                        .like(StringUtils.isNotBlank(keyword), "keyword", keyword)
                        .like(StringUtils.isNotBlank(productSn), "productSn", productSn)
                        .like(StringUtils.isNotBlank(productCategoryId), "productCategoryId", productCategoryId)
                        .like(StringUtils.isNotBlank(brandId), "brandId", brandId)
                        .orderBy("id", true)
        );
        return new PageUtils(page);
    }

    @Override
    public int updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail) {
        return 0;
    }

    @Override
    public int updatePublishStatus(List<Long> ids, Integer publishStatus) {
        return 0;
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        return 0;
    }

    @Override
    public int updateNewStatus(List<Long> ids, Integer newStatus) {
        return 0;
    }

    @Override
    public int updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
        return 0;
    }

    /**
     * 建立和插入关系表操作
     *
     * @param dao       可以操作的dao
     * @param dataList  要插入的数据
     * @param productId 建立关系的id
     */
    private void relateAndInsertList(Object dao, List dataList, Long productId) {
        try {
            if (CollectionUtils.isEmpty(dataList)) return;
            for (Object item : dataList) {
                Method setId = item.getClass().getMethod("setId", Long.class);
                setId.invoke(item, (Long) null);
                Method setProductId = item.getClass().getMethod("setProductId", Long.class);
                setProductId.invoke(item, productId);
            }
            Method insertList = dao.getClass().getMethod("insertList", List.class);
            insertList.invoke(dao, dataList);
        } catch (Exception e) {
            log.warning("创建产品出错:" + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    private void handleSkuStockCode(List<SkuStock> skuStockList, Long productId) {
        if (CollectionUtils.isEmpty(skuStockList)) return;
        for (int i = 0; i < skuStockList.size(); i++) {
            SkuStock skuStock = skuStockList.get(i);
            if (StringUtils.isEmpty(skuStock.getSkuCode())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                StringBuilder sb = new StringBuilder();
                //日期
                sb.append(sdf.format(new Date()));
                //四位商品id
                sb.append(String.format("%04d", productId));
                //三位索引id
                sb.append(String.format("%03d", i + 1));
                skuStock.setSkuCode(sb.toString());
            }
        }
    }
}
