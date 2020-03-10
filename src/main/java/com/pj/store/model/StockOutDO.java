package com.pj.store.model;


import lombok.Data;

import java.util.Date;

/**
 * 出库记录
 *
 * @author Ken
 */
@Data
public class StockOutDO {

    /**
     * 出库记录ID
     */
    private Integer id;

    /**
     * 客户ID
     */
    private Integer customerID;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 商品ID
     */
    private Integer goodID;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 出库仓库ID
     */
    private Integer repositoryID;

    /**
     * 商品出库数量
     */
    private long number;

    /**
     * 出库日期
     */
    private Date time;

    /**
     * 出库经手人
     */
    private String personInCharge;// 经手人


}
