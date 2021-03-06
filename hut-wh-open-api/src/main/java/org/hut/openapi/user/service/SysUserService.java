package org.hut.openapi.user.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.hut.common.entity.UserDTO;
import org.hut.common.entity.UserInfo;
import org.hut.common.entity.SysUser;
import org.hut.common.entity.Query;
import org.hut.common.entity.R;
import org.hut.common.vo.UserVO;

/**
 * Created by hutwanghui on 2018/11/24 20:07.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 用户接口
 */

public interface SysUserService extends IService<SysUser> {
    /**
     * 根据用户名查询用户角色信息
     *
     * @param username 用户名
     * @return userVo
     */
    UserVO findUserByUsername(String username);


    Boolean regist(SysUser sysUser);

    /**
     * 分页查询用户信息（含有角色信息）
     *
     * @param query  查询条件
     * @param userVO
     * @return
     */
    Page selectWithRolePage(Query query, UserVO userVO);

    /**
     * 查询用户信息
     *
     * @param userVo 角色名
     * @return userInfo
     */
    UserInfo findUserInfo(UserVO userVo);

    /**
     * 保存验证码
     *
     * @param randomStr 随机串
     * @param imageCode 验证码
     */
    void saveImageCode(String randomStr, String imageCode);

    /**
     * 删除用户
     *
     * @param sysUser 用户
     * @return boolean
     */
    Boolean deleteUserById(SysUser sysUser);

    /**
     * 更新当前用户基本信息
     *
     * @param userDto 用户信息
     * @return Boolean
     */
    R<Boolean> updateUserInfo(UserDTO userDto, String oldpassword, String password);

    /**
     * 更新指定用户信息
     *
     * @param userDto  用户信息
     * @param username 用户信息
     * @return
     */
    Boolean updateUser(UserDTO userDto, String username);

    /**
     * 通过手机号查询用户信息
     *
     * @param mobile 手机号
     * @return 用户信息
     */
    UserVO findUserByMobile(String mobile);


    /**
     * 通过邮箱地址查询用户信息
     *
     * @param email
     * @return
     */
    UserVO findUserByEmail(String email);

    /**
     * 发送验证码
     *
     * @param mobile 手机号
     * @return R
     */
    R<Boolean> sendSmsCode(String mobile);

    /**
     * 通过openId查询用户
     *
     * @param openId openId
     * @return 用户信息
     */
    UserVO findUserByOpenId(String openId);

    /**
     * 通过ID查询用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    UserVO selectUserVoById(Integer id);
}
