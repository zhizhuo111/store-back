package com.pj.store.model;

import lombok.Data;

/**
 * 登入登出记录DTO
 *
 * @author Ken
 * @since 2017/4/8.
 */
@Data
public class AccessRecordDTO {

    /**
     * 登入登出记录ID
     */
    private Integer id;

    /**
     * 登陆用户ID
     */
    private Integer userID;

    /**
     * 登陆用户名
     */
    private String userName;

    /**
     * 登入或登出时间
     */
    private String accessTime;

    /**
     * 用户登入或登出对应的IP地址
     */
    private String accessIP;

    /**
     * 记录类型，登入或登出
     */
    private String accessType;


}
