
package org.hut.common.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hutwanghui on 2018/11/24 13:24.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 字典
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@TableName("sys_dict")
public class SysDict extends Model<SysDict> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**
     * 数据值
     */
    private String value;
    /**
     * 标签名
     */
    private String label;
    /**
     * 类型
     */
    private String type;
    /**
     * 描述
     */
    private String description;
    /**
     * 排序（升序）
     */
    private BigDecimal sort;
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
     * 备注信息
     */
    private String remarks;
    /**
     * 删除标记
     */
    @TableField("del_flag")
    private String delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }




}
