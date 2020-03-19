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
}
