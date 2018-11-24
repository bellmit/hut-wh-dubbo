package org.hut.user.dto;

import org.hut.user.entity.SysRole;
import lombok.Data;

/**
 * Created by hutwanghui on 2018/11/24 15:48.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 角色信息
 */

@Data
public class RoleDTO extends SysRole {
    /**
     * 角色部门Id
     */
    private Integer roleDeptId;

    /**
     * 部门名称
     */
    private String deptName;
}
