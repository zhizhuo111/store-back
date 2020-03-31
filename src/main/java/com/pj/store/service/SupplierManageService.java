package com.pj.store.service;


import com.pj.store.model.Supplier;

import java.util.Map;

/**
 * 供应商信息管理 service
 *
 * @author Ken
 */
public interface SupplierManageService {
    /**
     * 返回指定supplierID 的供应商记录
     *
     * @param supplierId 供应商ID
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    Map<String, Object> selectById(Integer supplierId);

    /**
     * 返回指定 supplierName 的供应商记录
     * 支持查询分页以及模糊查询
     *
     * @param supplierName 供应商的名称
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    Map<String, Object> selectByName(String supplierName);

    /**
     * 查询所有的供应商记录
     *
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    Map<String, Object> selectAll();

    /**
     * 添加供应商记录
     *
     * @param supplier 供应商信息
     * @return 返回一个boolean值，值为true代表更新成功，否则代表失败
     */
    boolean addSupplier(Supplier supplier);

    /**
     * 更新供应商记录
     *
     * @param supplier 供应商信息
     * @return 返回一个boolean值，值为true代表更新成功，否则代表失败
     */
    boolean updateSupplier(Supplier supplier);

    boolean deleteSupplier(Integer supplierID);
}
