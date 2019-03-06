package org.hut.user.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.hut.common.entity.Query;
import org.hut.common.utils.DataScope;
import org.hut.common.vo.UserVO;
import org.hut.common.entity.SysUser;

import java.util.List;

/**
 * Created by hutwanghui on 2018/11/24 14:19.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 用户接口
 */

public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 通过用户名查询用户信息（含有角色信息）
     *
     * @param username 用户名
     * @return userVo
     */
    UserVO selectUserVoByUsername(String username);

    /**
     * 分页查询用户信息（含角色）
     *
     * @param query     查询条件
     * @param username  用户名
     * @param dataScope 数据权限
     * @return list
     */
    List selectUserVoPageDataScope(Query query, @Param("username") Object username, DataScope dataScope);

    /**
     * 通过手机号查询用户信息（含有角色信息）
     *
     * @param mobile 用户名
     * @return userVo
     */
    UserVO selectUserVoByMobile(String mobile);

    // TODO 邮箱注册
    // UserVO selectUserVoByEmail(String email);

    /**
     * 通过openId查询用户信息
     *
     * @param openId openid
     * @return userVo
     */
    UserVO selectUserVoByOpenId(String openId);

    /**
     * 通过ID查询用户信息
     *
     * @param id 用户ID
     * @return userVo
     */
    UserVO selectUserVoById(Integer id);
}