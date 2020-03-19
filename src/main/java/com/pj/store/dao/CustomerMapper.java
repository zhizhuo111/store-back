package com.pj.store.dao;

import com.pj.store.model.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CustomerMapper {
    Customer selectById(Integer cumtomerID);

    /**
     * 货物出库，客户下拉查询
     * @return
     */
    List<Customer> selectAll();

}
