package com.pj.store.model;

import lombok.Data;

/**
 * 仓库信息
 * @author Ken
 *
 */
@Data
public class Repository {

	private Integer id;// 仓库ID
	private String name;// 仓库名字
	private String address;// 仓库地址
	private String status;// 仓库状态
	private String area;// 仓库面积
	private String desc;// 仓库描述
	private Integer adminID;//仓库管理员ID
	private String adminName; //仓库管理员名字


}
