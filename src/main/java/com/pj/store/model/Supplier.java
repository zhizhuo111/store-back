package com.pj.store.model;

import lombok.Data;

/**
 * 供应商信息
 * 
 * @author Ken
 *
 */
@Data
public class Supplier {

	private Integer id;// 供应商ID
	private String name;// 供应商名
	private String personInCharge;// 负责人
	private String tel;// 联系电话
	private String email;// 电子邮件
	private String address;// 供应商地址


}
