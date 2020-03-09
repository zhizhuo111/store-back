package com.pj.store.model;

import lombok.Data;

/**
 * 货物信息
 * @author Ken
 *
 */
@Data
public class Goods {

	private Integer id;// 货物ID
	private String name;// 货物名
	private String type;// 货物类型
	private String size;// 货物规格
	private Double value;// 货物价值


}
