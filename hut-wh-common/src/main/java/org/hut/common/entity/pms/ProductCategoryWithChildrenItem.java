package org.hut.common.entity.pms;

import lombok.*;

import java.util.List;

/**
 * Created by hutwanghui on 2018/12/17.
 * email:zjjhwanhui@163.com
 * qq:472860892
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductCategoryWithChildrenItem extends ProductCategory {
    private List<ProductCategory> children;
}