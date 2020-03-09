package com.pj.store.model;

import lombok.Data;

/**
 * URL 的角色角色权限信息
 *
 * @author ken
 * @since 2017/2/26
 */
@Data
public class RolePermissionDO {

    /**
     * URL 的角色角色权限信息名称
     */
    private String name;

    /**
     * URL 的角色角色权限信息对应的 URL
     */
    private String url;

    /**
     * URL 的角色角色权限信息对应的角色
     */
    private String role;


}
