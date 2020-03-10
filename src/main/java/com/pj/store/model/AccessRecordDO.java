package com.pj.store.model;

import lombok.Data;

import java.util.Date;

/**
 * 用户登入登出记录
 *
 * @author Ken
 * @since 2017/3/5.
 */
@Data
public class AccessRecordDO {

    /**
     * 登入登出记录ID
     * 仅当该记录从数据库取出时有效
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
     * 记录类型，登入或登出
     */
    private String accessType;

    /**
     * 登入或登出时间
     */
    private Date accessTime;

    /**
     * 用户登入或登出对应的IP地址
     */
    private String accessIP;


}
