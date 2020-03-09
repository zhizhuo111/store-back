package com.pj.store.model;

import lombok.Data;

/**
 * 用户账户信息(数据传输对象)
 * @author ken
 * @since 2017/2/26.
 */
@Data
public class UserInfoDO {

    /**
     * 用户ID
     */
    private Integer userID;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码（已加密）
     */
    private String password;


}
