
package org.hut.common.entity;


import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.*;

import java.io.Serializable;

/**
 * Created by hutwanghui on 2018/11/24 13:29.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 角色菜单表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@TableName("sys_role_menu")
public class SysRoleMenu extends Model<SysRoleMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableId(type = IdType.INPUT)
    private Integer roleId;
    /**
     * 菜单ID
     */
    @TableId(type = IdType.INPUT)
    private Integer menuId;


    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }
}
