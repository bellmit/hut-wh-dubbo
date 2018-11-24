
package org.hut.user.entity;


import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * Created by hutwanghui on 2018/11/24 13:24.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 部门关系
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@TableName("sys_dept_relation")
public class SysDeptRelation extends Model<SysDeptRelation> {

    private static final long serialVersionUID = 1L;

    /**
     * 祖先节点
     */
    private Integer ancestor;
    /**
     * 后代节点
     */
    private Integer descendant;


    @Override
    protected Serializable pkVal() {
        return this.ancestor;
    }
}
