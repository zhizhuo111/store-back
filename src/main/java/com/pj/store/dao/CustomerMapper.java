package com.pj.store.dao;

import com.pj.store.model.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CustomerMapper {
    Customer selectById(Integer cumtomerID);
}
