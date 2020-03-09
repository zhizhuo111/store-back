package com.pj.store.model;

import lombok.Data;

/**
 * 出库/入库记录DO
 *
 * @author Ken
 * @since 2017/4/5.
 */
@Data
public class StockRecordDTO {

    /**
     * 记录ID
     */
    private Integer recordID;

    /**
     * 记录的类型（出库/入库）
     */
    private String type;

    /**
     * 供应商（入库）或客户（出库）名称
     */
    private String supplierOrCustomerName;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 出库或入库仓库ID
     */
    private Integer repositoryID;

    /**
     * 出库或入库数量
     */
    private long number;

    /**
     * 出库或入库时间
     */
    private String time;

    /**
     * 出库或入库经手人
     */
    private String personInCharge;


}
