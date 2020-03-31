package com.pj.store.service.impl;

import com.pj.store.dao.StockInMapper;
import com.pj.store.dao.SupplierMapper;
import com.pj.store.model.StockInDO;
import com.pj.store.model.Supplier;
import com.pj.store.service.SupplierManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private StockInMapper stockInMapper;
    /**
     * 返回指定supplierID 的供应商记录
     *
     * @param supplierId 供应商ID
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    @Override
    public Map<String, Object> selectById(Integer supplierId){
        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<>();
        List<Supplier> suppliers = new ArrayList<>();

        // 查询
        Supplier supplier = null;
        try {
            supplier = supplierMapper.selectById(supplierId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (supplier != null) {
            suppliers.add(supplier);
        }

        resultSet.put("data", suppliers);
        return resultSet;
    }

    /**
     * 返回指定 supplierName 的供应商记录 支持查询分页以及模糊查询
     *
     * @param supplierName 供应商德名称
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    @Override
    public Map<String, Object> selectByName(String supplierName){
        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<>();
        List<Supplier> suppliers = null;

        try {
                suppliers = supplierMapper.selectApproximateByName(supplierName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        resultSet.put("data", suppliers);
        return resultSet;
    }

    /**
     * 查询所有的供应商记录
     *
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    @Override
    public Map<String, Object> selectAll() {
        List<Supplier> suppliers = supplierMapper.selectAll();
        HashMap<String, Object> resultSet = new HashMap();
        resultSet.put("data", suppliers);
        return resultSet;
    }
    /**
     * 添加供应商记录
     *
     * @param supplier 供应商信息
     * @return 返回添加结果
     */
    @Override
    public boolean addSupplier(Supplier supplier) {

        // 插入新的记录
        if (supplier != null) {
            try {
                    // 检查重名
                    if (null == supplierMapper.selectByName(supplier.getName())) {
                        supplierMapper.insert(supplier);
                        if (supplier.getId() != null) {
                            return true;
                        }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 更新供应商记录
     *
     * @param supplier 供应商信息
     * @return 返回一个boolean值，值为true代表更新成功，否则代表失败
     */
    @Override
    public boolean updateSupplier(Supplier supplier) {

        // 更新记录
        if (supplier != null) {
            // 检验
            try {
                if (supplier != null) {
                    if (supplier.getId() != null) {
                        // 检查重名
                        Supplier supplierFromDB = supplierMapper.selectByName(supplier.getName());
                        if (supplierFromDB == null || supplier.getId().equals(supplierFromDB.getId())) {
                            supplierMapper.update(supplier);
                            return true;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 删除供应商记录
     *
     * @param supplierId 供应商ID
     * @return 返回一个boolean值，值为true代表更新成功，否则代表失败
     */
    @Override
    public boolean deleteSupplier(Integer supplierId) {
        // 查询该供应商是否有入库记录
        List<StockInDO> records = stockInMapper.selectBySupplierId(supplierId);
        if (records == null || records.size() > 0)
            return false;

        // 删除该条供应商记录
        supplierMapper.deleteById(supplierId);
        return true;
    }
}
