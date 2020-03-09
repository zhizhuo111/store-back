package com.pj.store.model;

import lombok.Data;

/**
 * 系统使用的角色信息
 * @author Ken
 *
 */
@Data
public class RoleDO {

	private Integer id;// 角色ID
	private String roleName;// 角色名
	private String roleDesc;// 角色描述


}
