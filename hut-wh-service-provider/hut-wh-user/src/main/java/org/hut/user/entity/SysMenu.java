
package org.hut.user.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hutwanghui on 2018/11/24 13:24.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 菜单权限
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@TableName("sys_menu")
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;
    /**
     * 菜单ID
     */
    @TableId(value = "menu_id", type = IdType.INPUT)
    private Integer menuId;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单权限标识
     */
    private String permission;
    /**
     * 请求链接
     */
    private String url;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 父菜单ID
     */
    @TableField("parent_id")
    private Integer parentId;
    /**
     * 图标
     */
    private String icon;
    /**
     * VUE页面
     */
    private String component;
    /**
     * 排序值
     */
    private Integer sort;
    /**
     * 菜单类型 （0菜单 1按钮）
     */
    private String type;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 0--正常 1--删除
     */
    @TableField("del_flag")
    private String delFlag;
    /**
     * 前端URL
     */
    private String path;


    @Override
    protected Serializable pkVal() {
        return null;
    }
}
