package com.pj.store.service.impl;

import com.pj.store.dao.SupplierMapper;
import com.pj.store.model.Supplier;
import com.pj.store.service.SupplierManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 供应商信息管理 service 实现类
 *
 * @author Ken
 */
@Service
public class SupplierManageServiceImpl implements SupplierManageService {
    @Autowired
    SupplierMapper supplierMapper;
    @Override
    public Map<String, Object> selectAll() {
        List<Supplier> suppliers = supplierMapper.selectAll();
        HashMap<String, Object> resultSet = new HashMap();
        resultSet.put("data", suppliers);
        return resultSet;
    }
}
