
package org.hut.user.entity;


import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.*;

import java.io.Serializable;

/**
 * Created by hutwanghui on 2018/11/24 13:31.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 第三方授权登陆
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@TableName("sys_oauth_client_details")
public class SysOauthClientDetails extends Model<SysOauthClientDetails> {

    private static final long serialVersionUID = 1L;
    @TableId(value = "client_id", type = IdType.INPUT)
    private String clientId;
    @TableField("resource_ids")
    private String resourceIds;
    @TableField("client_secret")
    private String clientSecret;
    private String scope;
    @TableField("authorized_grant_types")
    private String authorizedGrantTypes;
    @TableField("web_server_redirect_uri")
    private String webServerRedirectUri;
    private String authorities;
    @TableField("access_token_validity")
    private Integer accessTokenValidity;
    @TableField("refresh_token_validity")
    private Integer refreshTokenValidity;
    @TableField("additional_information")
    private String additionalInformation;
    private String autoapprove;

    @Override
    protected Serializable pkVal() {
        return this.clientId;
    }
}
