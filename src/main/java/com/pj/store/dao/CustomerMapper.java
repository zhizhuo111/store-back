package com.pj.store.dao;

import com.pj.store.model.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CustomerMapper {

    /**
     * 货物出库，客户下拉查询
     * @return
     */
    List<Customer> selectAll();

    List<Customer> selectAlls();

    /**
     * 选择指定 id 的 Supplier
     * @param id Customer的ID
     * @return 返回指定ID对应的Customer
     */
    Customer selectById(Integer id);

    /**
     * 选择指定 Customer name 的 customer
     * @param customerName 客户的名称
     * @return 返回指定CustomerName对应的Customer
     */
    Customer selectByName(String customerName);

    /**
     * 选择指定 customer name 的 Customer
     * 与 selectByName 方法的区别在于本方法将返回相似匹配的结果
     * @param customerName Customer 供应商名
     * @return 返回模糊匹配指定customerName 对应的Customer
     */
    List<Customer> selectApproximateByName(String customerName);

	void insert(Customer customer);

    /**
     * 更新 Customer 到数据库
     * 该 Customer 必须已经存在于数据库中，即已经分配主键，否则将更新失败
     * @param customer customer 实例
     */
	void update(Customer customer);

    /**
     * 删除指定 id 的 customer
     * @param id customer ID
     */
    void deleteById(Integer id);
}
