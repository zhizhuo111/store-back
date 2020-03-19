package com.pj.store.service.impl;

import com.pj.store.dao.CustomerMapper;
import com.pj.store.model.Customer;
import com.pj.store.service.CustomerManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerManageServiceImpl implements CustomerManageService {
    @Autowired
    CustomerMapper customerMapper;

    /**
     * 货物出库，客户下拉查询
     * @return
     */
    @Override
    public List<Customer> selectAll() {
        List<Customer> result = customerMapper.selectAll();
        return result;
    }
}
