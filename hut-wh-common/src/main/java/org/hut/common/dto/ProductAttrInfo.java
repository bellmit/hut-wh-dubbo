package org.hut.common.dto;

import lombok.*;

/**
 * Created by hutwanghui on 2018/12/17.
 * email:zjjhwanhui@163.com
 * qq:472860892
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductAttrInfo {
    private Long attributeId;
    private Long attributeCategoryId;
}
