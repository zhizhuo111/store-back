package com.pj.store.service.impl;

import com.pj.store.dao.CustomerMapper;
import com.pj.store.dao.StockOutMapper;
import com.pj.store.model.Customer;
import com.pj.store.model.StockOutDO;
import com.pj.store.service.CustomerManageService;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerManageServiceImpl implements CustomerManageService {
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    private StockOutMapper stockOutMapper;
    @Autowired
    private CustomerManageService customerManageService;
    /**
     * 货物出库，客户下拉查询
     * @return
     */
    @Override
    public List<Customer> selectAll() {
        List<Customer> result = customerMapper.selectAll();
        return result;
    }

    /**
     * 返回指定customer id 的客户记录
     *
     * @param customerId 客户ID
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    @Override
    public Map<String, Object> selectById(Integer customerId) {
        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<>();
        List<Customer> customers = new ArrayList<>();
        long total = 0;

        // 查询
        Customer customer = null;
        try {
            customer = customerMapper.selectById(customerId);
        } catch (Exception e) {
            System.out.println("exception catch");
            e.printStackTrace();
        }

        if (customer != null) {
            customers.add(customer);
            total = 1;
        }

        resultSet.put("data", customers);
        resultSet.put("total", total);
        return resultSet;
    }

    /**
     * 返回指定 customer name 的客户记录 支持查询分页以及模糊查询
     *
//     * @param offset       分页的偏移值
//     * @param limit        分页的大小
     * @param customerName 客户的名称
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    @Override
    public Map<String, Object> selectByName(String customerName) {
        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<>();
        List<Customer> customers = null;


        // query
        try {
                customers = customerMapper.selectApproximateByName(customerName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        resultSet.put("data", customers);
        return resultSet;
    }

    /**
     * 分页查询客户的记录
     *
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    @Override
    public Map<String, Object> selectAlls() {
        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<>();
        List<Customer> customers = null;
        long total = 0;
        boolean isPagination = true;

        try {
                customers = customerMapper.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        resultSet.put("data", customers);
        resultSet.put("total", total);
        return resultSet;
    }

    /**
     * 添加客户信息
     *
     * @param customer 客户信息
     * @return 返回一个boolean值，值为true代表更新成功，否则代表失败
     */
    @Override
    public boolean addCustomer(Customer customer) {
//        // 插入新的记录
                    if (null == customerMapper.selectByName(customer.getName())) {
                        System.out.println(customer);
                        customerMapper.insert(customer);
                        return true;
                    }
        return false;
    }

    /**
     * 更新客户信息
     *
     * @param customer 客户信息
     * @return 返回一个boolean值，值为true代表更新成功，否则代表失败
     */
    @Override
    public boolean updateCustomer(Customer customer) {

        // 更新记录
        if (customer != null) {
            // 检验
                    // 检查重名
//                    Customer customerFromDB = customerMapper.selectByName(customer.getName());
//                    if (customerFromDB == null || customerFromDB.getId().equals(customer.getId())) {
                        customerMapper.update(customer);
                        return true;
//                    }
        }
        return false;
    }

    /**
     * 删除客户信息
     *
     * @param customerId 客户ID
     * @return 返回一个boolean值，值为true代表更新成功，否则代表失败
     */
    @Override
    public boolean deleteCustomer(Integer customerId) {
            // 查询该客户是否有出库记录
            List<StockOutDO> records = stockOutMapper.selectByCustomerId(customerId);
            if (records != null && records.size() > 0) {
                return false;
            } else {
                // 删除该条客户记录
                customerMapper.deleteById(customerId);
                return true;
            }
    }


}