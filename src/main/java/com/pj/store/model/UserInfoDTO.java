package com.pj.store.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户账户信息（数据传输对象）
 * @author ken
 * @since 2017/2/26.
 */
@Data
public class UserInfoDTO {

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

    /**
     * 用户角色
     */
    private List<String> role = new ArrayList<>();

}
