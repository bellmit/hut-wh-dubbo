

package org.hut.common.entity;

import org.hut.common.entity.SysUser;
import lombok.Data;

import java.util.List;
/**
 * Created by hutwanghui on 2018/11/24 15:51.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc:
 */

@Data
public class UserDTO extends SysUser {
    /**
     * 角色ID
     */
    private List<Integer> role;

    private Integer deptId;

    /**
     * 新密码
     */
    private String newpassword1;
}
