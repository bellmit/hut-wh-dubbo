package org.hut.product.service;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang.StringUtils;
import org.hut.common.dto.ProductCategoryParam;
import org.hut.common.entity.pms.Product;
import org.hut.common.entity.pms.ProductCategory;
import org.hut.common.entity.pms.ProductCategoryAttributeRelation;
import org.hut.common.entity.pms.ProductCategoryWithChildrenItem;
import org.hut.common.utils.PageUtils;
import org.hut.common.utils.Query;
import org.hut.product.mapper.ProductCategoryAttributeRelationMapper;
import org.hut.product.mapper.ProductCategoryMapper;
import org.hut.openapi.user.service.pms.IProductCategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.hut.product.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品分类 服务实现类
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
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements IProductCategoryService {

    @Override
    public boolean insertProductCategoryParam(ProductCategoryParam productCategoryParam) {
        try {
            ProductCategory productCategory = ProductCategory.builder().build();
            //新创建的产品分类肯定是没有产品的，所以指定为0
            productCategory.setProductCount(0);
            //进行属性的拷贝
            BeanUtil.copyProperties(productCategoryParam, productCategory);
            //没有父分类的时候，设置为一级分类
            setCategoryLevel(productCategory);
            //创建筛选属性关联
            List<Long> productAttributeIdList = productCategoryParam.getProductAttributeIdList();
            if (!CollectionUtils.isEmpty(productAttributeIdList)) {
                insertRelationList(productCategory.getId(), productAttributeIdList);
            }
            return this.insert(productCategory);
        } catch (Exception e) {
            //进行回滚操作
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("===========发生事务回滚===========");
            e.printStackTrace();
        }
        return false;
    }

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductCategoryAttributeRelationMapper productCategoryAttributeRelationMapper;

    @Override
    public boolean updateProductCategoryParam(Long id, ProductCategoryParam pmsProductCategoryParam) {
        try {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setId(id);
            BeanUtil.copyProperties(pmsProductCategoryParam, productCategory);
            setCategoryLevel(productCategory);
            //更新商品分类时要更新商品中的名称
            List<Product> productList = productMapper.selectByProductCategoryId(id);
            productList.stream().map(p -> {
                p.setProductCategoryName(pmsProductCategoryParam.getName());
                return p;
            }).forEach(p -> productMapper.updateById(p));
            //同时更新筛选属性的信息
            if (!CollectionUtils.isEmpty(pmsProductCategoryParam.getProductAttributeIdList())) {
                updateRelationList(id, pmsProductCategoryParam.getProductAttributeIdList());
            }
            return this.updateById(productCategory);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("===========发生事务回滚===========");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateNavStatusBeatch(List<Long> ids, Integer navStatus) {
        try {
            List<ProductCategory> productCategoryList = this.selectBatchIds(ids);
            productCategoryList.stream().forEach(p -> p.setNavStatus(navStatus));
            return this.updateBatchById(productCategoryList);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("===========发生事务回滚===========");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateShowStatusBeatch(List<Long> ids, Integer showStatus) {
        try {
            List<ProductCategory> productCategoryList = this.selectBatchIds(ids);
            productCategoryList.stream().forEach(p -> p.setShowStatus(showStatus));
            return this.updateBatchById(productCategoryList);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("===========发生事务回滚===========");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String tag = (String) params.get("name");
        Page<ProductCategory> page = this.selectPage(
                new Query<ProductCategory>(params).getPage(),
                new EntityWrapper<ProductCategory>()
                        .like(StringUtils.isNotBlank(tag), "name", tag)
                        .orderBy("id", true)
        );
        return new PageUtils(page);
    }


    @Override
    public List<ProductCategoryWithChildrenItem> listWithChildren() {
        return this.baseMapper.listWithChildren();
    }

    /**
     * 根据分类的parentId设置分类的level
     */
    private void setCategoryLevel(ProductCategory productCategory) {
        //没有父分类时为一级分类
        if (productCategory.getParentId() == 0) {
            productCategory.setLevel(0);
        } else {
            //有父分类时选择根据父分类level设置
            ProductCategory parentCategory = this.baseMapper.selectById(productCategory.getParentId());
            if (parentCategory != null) {
                productCategory.setLevel(parentCategory.getLevel() + 1);
            } else {
                productCategory.setLevel(0);
            }
        }
    }

    /**
     * 批量插入商品分类与筛选属性关系表
     *
     * @param productCategoryId      商品分类id
     * @param productAttributeIdList 相关商品筛选属性id集合
     */
    private void insertRelationList(Long productCategoryId, List<Long> productAttributeIdList) {
        List<ProductCategoryAttributeRelation> relationList = new ArrayList<>();
        for (Long productAttrId : productAttributeIdList) {
            ProductCategoryAttributeRelation relation = new ProductCategoryAttributeRelation();
            relation.setProductAttributeId(productAttrId);
            relation.setProductCategoryId(productCategoryId);
            relationList.add(relation);
        }
        productCategoryAttributeRelationMapper.insertList(relationList);
    }

    private void updateRelationList(Long productCategoryId, List<Long> productAttributeIdList) {
        List<ProductCategoryAttributeRelation> relationList = new ArrayList<>();
        for (Long productAttrId : productAttributeIdList) {
            ProductCategoryAttributeRelation relation = new ProductCategoryAttributeRelation();
            relation.setProductAttributeId(productAttrId);
            relation.setProductCategoryId(productCategoryId);
            relationList.add(relation);
        }
        productCategoryAttributeRelationMapper.updateList(relationList);
    }
}
