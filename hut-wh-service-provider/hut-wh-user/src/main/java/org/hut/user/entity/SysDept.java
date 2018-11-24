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
 * Created by hutwanghui on 2018/11/24 13:23.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: Sys部门
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@TableName("sys_dept")
public class SysDept extends Model<SysDept> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "dept_id", type = IdType.AUTO)
    private Integer deptId;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 排序
     */
    @TableField("order_num")
    private Integer orderNum;
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
     * 是否删除  -1：已删除  0：正常
     */
    @TableField("del_flag")
    private String delFlag;

    @TableField("parent_id")
    private Integer parentId;


    @Override
    protected Serializable pkVal() {
        return this.deptId;
    }
}
