
package org.hut.user.entity;


import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.*;

import java.io.Serializable;

/**
 * Created by hutwanghui on 2018/11/24 13:29.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 角色与部门之间的关系
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@TableName("sys_role_dept")
public class SysRoleDept extends Model<SysRoleDept> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 角色ID
     */
    @TableField("role_id")
    private Integer roleId;
    /**
     * 部门ID
     */
    @TableField("dept_id")
    private Integer deptId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
