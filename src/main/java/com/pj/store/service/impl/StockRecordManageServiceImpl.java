package com.pj.store.service.impl;

import com.pj.store.dao.*;
import com.pj.store.model.*;
import com.pj.store.service.StockRecordManageService;
import com.pj.store.service.StorageManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class StockRecordManageServiceImpl implements StockRecordManageService {
    @Autowired
    private SupplierMapper supplierMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private RepositoryMapper repositoryMapper;
    @Autowired
    private StockInMapper stockinMapper;
    @Autowired
    private StockOutMapper stockOutMapper;
    @Autowired
    private StorageManageService storageManageService;

    /**
     * 根据条件查询出入库记录
     * @param repositoryID
     * @param startDateStr
     * @param endDateStr
     * @param searchType
     * @return
     */
    @Override
    public Map<String, Object> selectStockRecord(Integer repositoryID, String startDateStr, String endDateStr, String searchType){
        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<>();
        // 转换 Date 对象
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            if(startDateStr != null){
                startDate = dateFormat.parse(startDateStr);
            }
            if(endDateStr != null){
                endDate = dateFormat.parse(endDateStr);
            }
        }catch (Exception e){
           e.printStackTrace();
        }
        // 根据查询模式执行查询
        List<StockRecordDTO> stockRecordDTOS = new ArrayList<>();
        Map<String, Object> stockInTemp;
        Map<String, Object> stockOutTemp;
        List<StockInDO> stockInRecordDOS = null;
        List<StockOutDO> stockOutRecordDOS = null;

        switch (searchType) {
            case "all": {
                    stockInTemp = selectStockInRecord(repositoryID, startDate, endDate);
                    stockOutTemp = selectStockOutRecord(repositoryID, startDate, endDate);
                    stockInRecordDOS = (List<StockInDO>) stockInTemp.get("data");
                    stockOutRecordDOS = (List<StockOutDO>) stockOutTemp.get("data");
                }
                break;
            case "stockInOnly": {
                stockInTemp = selectStockInRecord(repositoryID, startDate, endDate);
                stockInRecordDOS = (List<StockInDO>) stockInTemp.get("data");
                break;
            }
            case "stockOutOnly": {
                stockOutTemp = selectStockOutRecord(repositoryID, startDate, endDate);
                stockOutRecordDOS = (List<StockOutDO>) stockOutTemp.get("data");
                break;
            }
            case "none": {
                break;
            }
        }
        if (stockInRecordDOS != null)
            stockInRecordDOS.forEach(stockInDO -> stockRecordDTOS.add(stockInRecordConvertToStockRecordDTO(stockInDO)));
        if (stockOutRecordDOS != null)
            stockOutRecordDOS.forEach(stockOutDO -> stockRecordDTOS.add(stockOutDoConvertToStockRecordDTO(stockOutDO)));

        resultSet.put("data", stockRecordDTOS);
        return resultSet;
    }

    /**
     *  货物入库操作
     * @param supplierID
     * @param goodsID
     * @param number
     * @return
     */
    @Override
    public boolean stockInOperation(Integer supplierID, Integer goodsID, Integer repositoryID, long number) {
        if (repositoryID == null)
            return false;

        // 检查入库数量有效性
        if (number < 0)
            return false;

        boolean isSuccess = false;
        try {
            // 更新库存记录
            isSuccess = storageManageService.storageIncrease(goodsID, repositoryID, number);

            // 保存入库记录
            if (isSuccess) {
                StockInDO stockInDO = new StockInDO();
                stockInDO.setSupplierID(supplierID);
                stockInDO.setGoodID(goodsID);
                stockInDO.setNumber(number);
                stockInDO.setTime(new Date());
                stockInDO.setRepositoryID(repositoryID);
                stockInDO.setPersonInCharge("admin");
                System.out.println("----------------");
                System.out.println(stockInDO);
                stockinMapper.insert(stockInDO);

            }

            return isSuccess;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    /**
     * 货物出库操作
     *
     * @param customerID   客户ID
     * @param goodsID      货物ID
     * @param repositoryID 出库仓库ID
     * @param number       出库数量
     * @return 返回一个boolean值，若值为true表示出库成功，否则表示出库失败
     */
    @Override
    public boolean stockOutOperation(Integer customerID, Integer goodsID, Integer repositoryID, long number){

        // 检查ID对应的记录是否存在
        if (!(customerValidate(customerID) && goodsValidate(goodsID) && repositoryValidate(repositoryID)))
            return false;

        // 检查出库数量范围是否有效
        if (number < 0)
            return false;

        boolean isSuccess = false;
        try {
            // 更新库存信息
            isSuccess = storageManageService.storageDecrease(goodsID, repositoryID, number);

            // 保存出库记录
            if (isSuccess) {
                StockOutDO stockOutDO = new StockOutDO();
                stockOutDO.setCustomerID(customerID);
                stockOutDO.setGoodID(goodsID);
                stockOutDO.setNumber(number);
                stockOutDO.setPersonInCharge("admin");
                stockOutDO.setRepositoryID(repositoryID);
                stockOutDO.setTime(new Date());
                stockOutMapper.insert(stockOutDO);
            }

            return isSuccess;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    /**
     * 查询入库
     * @param repositoryID
     * @param startDate
     * @param endDate
     * @return
     */
    private Map<String, Object> selectStockInRecord(Integer repositoryID, Date startDate, Date endDate){
        Map<String, Object> result = new HashMap<>();
        List<StockInDO> stockInRecords = null;
//        boolean isPagination = true;

//        // 检查是否需要分页查询
//        if (offset < 0 || limit < 0)
//            isPagination = false;

        // 查询记录
        try {
//            if (isPagination) {
//                PageHelper.offsetPage(offset, limit);
//                stockInRecords = stockinMapper.selectByRepositoryIDAndDate(repositoryID, startDate, endDate);
//                if (stockInRecords != null)
//                    stockInTotal = new PageInfo<>(stockInRecords).getTotal();
//                else
//                    stockInRecords = new ArrayList<>(10);
//            } else {
                stockInRecords = stockinMapper.selectByRepositoryIDAndDate(repositoryID, startDate, endDate);
//                if (stockInRecords != null)
//                    stockInTotal = stockInRecords.size();
//                else
//                    stockInRecords = new ArrayList<>(10);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        result.put("data", stockInRecords);
        return result;
    }

    /**
     * 查询出库
     * @param repositoryID
     * @param startDate
     * @param endDate
     * @return
     */
    private Map<String, Object> selectStockOutRecord(Integer repositoryID, Date startDate, Date endDate) {
        Map<String, Object> result = new HashMap<>();
        List<StockOutDO> stockOutRecords = null;
        long stockOutRecordTotal = 0;
        boolean isPagination = true;


        // 查询记录
        try {
            if (isPagination) {
//                PageHelper.offsetPage(offset, limit);
                stockOutRecords = stockOutMapper.selectByRepositoryIDAndDate(repositoryID, startDate, endDate);
//                if (stockOutRecords != null)
////                    stockOutRecordTotal = new PageInfo <>(stockOutRecords).getTotal();
//                else
//                    stockOutRecords = new ArrayList<>(10);
            } else {
                stockOutRecords = stockOutMapper.selectByRepositoryIDAndDate(repositoryID, startDate, endDate);
                if (stockOutRecords != null)
                    stockOutRecordTotal = stockOutRecords.size();
                else
                    stockOutRecords = new ArrayList<>(10);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        result.put("data", stockOutRecords);
        return result;
    }

    /**
     * 将 StockInDO 转换为 StockRecordDTO
     *
     * @param stockInDO StockInDO 对象
     * @return 返回 StockRecordDTO 对象
     */
    private StockRecordDTO stockInRecordConvertToStockRecordDTO(StockInDO stockInDO) {
        StockRecordDTO stockRecordDTO = new StockRecordDTO();
        stockRecordDTO.setRecordID(stockInDO.getId());
        stockRecordDTO.setSupplierOrCustomerName(stockInDO.getSupplierName());
        stockRecordDTO.setGoodsName(stockInDO.getGoodName());
        stockRecordDTO.setNumber(stockInDO.getNumber());
        stockRecordDTO.setTime(dateFormat.format(stockInDO.getTime()));
        stockRecordDTO.setRepositoryID(stockInDO.getRepositoryID());
        stockRecordDTO.setPersonInCharge(stockInDO.getPersonInCharge());
        stockRecordDTO.setType("入库");
        return stockRecordDTO;
    }
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm");
    /**
     * 将 StockOutDO 转换为 StockRecordDTO 对象
     *
     * @param stockOutDO StockOutDO 对象
     * @return 返回 StockRecordDTO 对象
     */
    private StockRecordDTO stockOutDoConvertToStockRecordDTO(StockOutDO stockOutDO) {
        StockRecordDTO stockRecordDTO = new StockRecordDTO();
        stockRecordDTO.setRecordID(stockOutDO.getId());
        stockRecordDTO.setSupplierOrCustomerName(stockOutDO.getCustomerName());
        stockRecordDTO.setGoodsName(stockOutDO.getCustomerName());
        stockRecordDTO.setNumber(stockOutDO.getNumber());
        stockRecordDTO.setTime(dateFormat.format(stockOutDO.getTime()));
        stockRecordDTO.setRepositoryID(stockOutDO.getRepositoryID());
        stockRecordDTO.setPersonInCharge(stockOutDO.getPersonInCharge());
        stockRecordDTO.setType("出库");
        return stockRecordDTO;
    }


    /**
     * 检查货物ID对应的记录是否存在
     *
     * @param goodsID 货物ID
     * @return 若存在则返回true，否则返回false
     */
    private boolean goodsValidate(Integer goodsID) {
        try {
            Goods goods = goodsMapper.selectById(goodsID);
            return goods != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 检查仓库ID对应的记录是否存在
     *
     * @param repositoryID 仓库ID
     * @return 若存在则返回true，否则返回false
     */
    private boolean repositoryValidate(Integer repositoryID) {
        try {
            Repository repository = repositoryMapper.selectByID(repositoryID);
            return repository != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 检查供应商ID对应的记录是否存在
     *
     * @param supplierID 供应商ID
     * @return 若存在则返回true，否则返回false
     */
    private boolean supplierValidate(Integer supplierID){
            Supplier supplier = supplierMapper.selectById(supplierID);
            return supplier != null;
    }

    /**
     * 检查客户ID对应的记录是否存在
     *
     * @param cumtomerID 客户ID
     * @return 若存在则返回true，否则返回false
     */
    private boolean customerValidate(Integer cumtomerID){
            Customer customer = customerMapper.selectById(cumtomerID);
            return customer != null;
    }
}
