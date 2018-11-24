package org.hut.common.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * Created by hutwanghui on 2018/11/24.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * DataScope实现数据权限，用于部分的上下级层级，来确定数据的展示范围。
 */
@Data
public class DataScope extends HashMap {
    /**
     * 限制范围的字段名称
     */
    private String scopeName = "dept_id";

    /**
     * 具体的数据范围
     */
    private List<Integer> deptIds;

    /**
     * 是否只查询本部门
     */
    private Boolean isOnly = false;
}
