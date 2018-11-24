
package org.hut.user.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.*;

import java.io.Serializable;


/**
 * Created by hutwanghui on 2018/11/24 13:30.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 用户角色表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@TableName("sys_user_role")
public class SysUserRole extends Model<SysUserRole> {
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    @TableId(type = IdType.INPUT)
    private Integer userId;
    /**
     * 角色ID
     */
    @TableId(type = IdType.INPUT)
    private Integer roleId;

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }
}
