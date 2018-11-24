package org.hut.user.dto;

import org.hut.user.entity.SysUser;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by hutwanghui on 2018/11/24 15:47.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 用户信息对象
 */

@Data
public class UserInfo implements Serializable {
    /**
     * 用户基本信息
     */
    private SysUser sysUser;
    /**
     * 权限标识集合
     */
    private String[] permissions;

    /**
     * 角色集合
     */
    private String[] roles;
}
