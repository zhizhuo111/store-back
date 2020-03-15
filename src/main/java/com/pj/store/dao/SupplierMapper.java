package com.pj.store.dao;

import com.pj.store.model.Supplier;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SupplierMapper {
    Supplier selectById(Integer supplierID);

    /**
     * 选择全部的 Supplier
     * @return 返回所有的供应商
     */
    List<Supplier> selectAll();
}
