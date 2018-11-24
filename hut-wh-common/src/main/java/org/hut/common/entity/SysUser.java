
package org.hut.common.entity;


import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hutwanghui on 2018/11/24 13:32.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 用户
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;


    /**
     * 主键ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    /**
     * 用户名
     */
    private String username;

    private String password;
    /**
     * 随机盐
     */
    private String salt;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 0-正常，1-删除
     */
    @TableField("del_flag")
    private String delFlag;

    /**
     * 简介
     */
    private String phone;
    /**
     * 头像
     */
    private String avatar;

    /**
     * 部门ID
     */
    @TableField("dept_id")
    private Integer deptId;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }
}
