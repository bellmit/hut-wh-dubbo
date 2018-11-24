
package org.hut.user.mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.hut.common.entity.Query;
import org.hut.user.entity.SysRole;

import java.util.List;
import java.util.Map;

/**
 * Created by hutwanghui on 2018/11/24 14:26.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 角色接口
 */

public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 查询角色列表含有部门信息
     *
     * @param query     查询对象
     * @param condition 条件
     * @return List
     */
    List<Object> selectRolePage(Query<Object> query, Map<String, Object> condition);

    /**
     * 通过部门ID查询角色列表
     *
     * @param deptId 部门ID
     * @return 角色列表
     */
    List<SysRole> selectListByDeptId(Integer deptId);
}