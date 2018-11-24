package org.hut.common.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hutwanghui on 2018/11/24.
 * email:zjjhwanhui@163.com
 * qq:472860892
 */
@Data
public class TreeNode {
    protected int id;
    protected int parentId;
    protected List<TreeNode> children = new ArrayList<TreeNode>();

    public void add(TreeNode node) {
        this.children.add(node);
    }
}
