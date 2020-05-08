package com.pj.store.controller;

import com.pj.store.model.UserInfoDTO;
import com.pj.store.service.StockRecordManageService;
import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/stockRecordManage")
public class StockRecordManageController {
	@Autowired
	StockRecordManageService stockRecordManageService;

	/**
	 * 货物入库操作
	 *
	 * @param supplierID      供应商ID
	 * @param goodsID         货物ID
	 * @param repositoryIDStr 仓库ID
	 * @param number          入库数目
	 * @return 返回一个map，key为result的值表示操作是否成功
	 */
	@RequestMapping("stockIn")
	@ResponseBody
	public boolean stockIn(@RequestParam("supplierID") String supplierIDStr,
						   @RequestParam("goodsID") String goodsIDStr,
						   @RequestParam(value = "repositoryID", required = false) String repositoryIDStr,
						   @RequestParam("number") String numberStr,
						   @RequestParam("userName") String userName) {
		System.out.println(supplierIDStr);
		System.out.println(goodsIDStr);
		System.out.println(repositoryIDStr);
		System.out.println(numberStr);
		if (supplierIDStr == "" || goodsIDStr == "" || repositoryIDStr == "" || numberStr == "") {
			return false;
		}
		Integer supplierID = null;
		Integer goodsID = null;
		Integer repositoryID = null;
		Integer number = null;
		// 参数检查
		if (supplierIDStr != null) {
			supplierID = Integer.valueOf(supplierIDStr);
		}
		if (goodsIDStr != null) {
			goodsID = Integer.valueOf(goodsIDStr);
		}
		if (repositoryIDStr != null) {
			repositoryID = Integer.valueOf(repositoryIDStr);
		}
		if (numberStr != null) {
			number = Integer.valueOf(numberStr);
			Long numberLong = number.longValue();
		}
		boolean result = stockRecordManageService.stockInOperation(supplierID, goodsID, repositoryID, number, userName);

		return result;
	}

	/**
	 * 货物出库操作
	 *
	 * @param customerID      客户ID
	 * @param goodsID         货物ID
	 * @param repositoryIDStr 仓库ID
	 * @param number          出库数量
	 * @return 返回一个map，key为result的值表示操作是否成功
	 */
	@RequestMapping(value = "stockOut", method = RequestMethod.POST)
	public
	@ResponseBody
	boolean stockOut(@RequestParam("customerID") Integer customerID,
					 @RequestParam("goodsID") Integer goodsID,
					 @RequestParam(value = "repositoryID", required = false) String repositoryIDStr,
					 @RequestParam("number") long number) {
		System.out.println(customerID);
		System.out.println(goodsID);
		System.out.println(repositoryIDStr);
		System.out.println(number);
		Integer repositoryID = null;

		// 参数检查
		if (repositoryIDStr != null) {
			repositoryID = Integer.valueOf(repositoryIDStr);
		}

		boolean result = stockRecordManageService.stockOutOperation(customerID, goodsID, repositoryID, number);
		return result;
	}

	/**
	 * @param searchType      查詢出/入库记录
	 * @param repositoryIDStr 查询对应的仓库
	 * @return
	 */
	@RequestMapping("searchStockRecord")
	@ResponseBody
	public Map<String, Object> getStockRecord(String searchType, String repositoryIDStr, String beginDate, String endDate) {
		System.out.println(searchType);
		System.out.println(repositoryIDStr);
		System.out.println(beginDate);
		System.out.println(endDate);
		System.out.println("============");
		Integer repositoryID = -1;
		Map<String, Object> queryResult = null;
		if (repositoryIDStr != "") {
			repositoryID = Integer.valueOf(repositoryIDStr);
		}
		try {
			queryResult = stockRecordManageService.selectStockRecord(repositoryID, beginDate, endDate, searchType);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("============");
		System.out.println(queryResult);
		return queryResult;
	}

}
