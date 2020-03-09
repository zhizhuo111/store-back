package com.pj.store.model;

import lombok.Data;

import java.sql.Date;

/**
 * 仓库管理员信息
 * @author Ken
 *
 */
@Data
public class RepositoryAdmin {

	private Integer id;// 仓库管理员ID
	private String name;// 姓名
	private String sex;// 性别
	private String tel;// 联系电话
	private String address;// 地址
	private Date birth;// 出生日期
	private Integer repositoryBelongID;// 所属仓库ID

	

}
