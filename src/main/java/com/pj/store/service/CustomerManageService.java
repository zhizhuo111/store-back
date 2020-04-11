package com.pj.store.service;

import com.pj.store.model.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerManageService {
    /**
     * 货物出库，客户下拉查询
     * @return
     */
    List<Customer> selectAll();

	/**
	 * 分页查询客户的记录
	 *
	 * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
	 */
	Map<String, Object> selectAlls();
	/**
	 * 返回指定customer id 的客户记录
	 *
	 * @param customerId 客户ID
	 * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
	 */
	Map<String, Object> selectById(Integer customerId);

	/**
	 * 返回指定 customer name 的客户记录
	 * 支持查询分页以及模糊查询
	 *
	 * @param customerName 客户的名称
	 * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
	 */
	Map<String, Object> selectByName(String customerName);

	/**
	 * 添加客户信息
	 *
	 * @param customer 客户信息
	 * @return 返回一个boolean值，值为true代表更新成功，否则代表失败
	 */
	boolean addCustomer(Customer customer);

	/**
	 * 更新客户信息
	 *
	 * @param customer 客户信息
	 * @return 返回一个boolean值，值为true代表更新成功，否则代表失败
	 */
	boolean updateCustomer(Customer customer);

	/**
	 * 删除客户信息
	 *
	 * @param customerId 客户ID
	 * @return 返回一个boolean值，值为true代表更新成功，否则代表失败
	 */
	boolean deleteCustomer(Integer customerId);
}
