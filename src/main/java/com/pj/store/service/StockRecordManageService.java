package com.pj.store.service;

import java.text.ParseException;
import java.util.Map;

public interface StockRecordManageService {
    /**
     * 查询出入库记录
     *
     * @param repositoryID 仓库ID
     * @param endDateStr   查询记录起始日期
     * @param startDateStr 查询记录结束日期
     * @param searchType   记录查询方式
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    Map<String, Object> selectStockRecord(Integer repositoryID, String startDateStr, String endDateStr, String searchType) throws ParseException;

    /**
     * 货物     * @param supplierID
     * @param goodsID
     * @param repositoryIDStr
     * @param number
     * @return
     */
    boolean stockInOperation(Integer supplierID, Integer goodsID, Integer repositoryIDStr, long number, String userName);

    boolean stockOutOperation(Integer customerID, Integer goodsID, Integer repositoryID, long number);
}
