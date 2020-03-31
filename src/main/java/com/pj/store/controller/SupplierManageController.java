package com.pj.store.controller;

import com.pj.store.model.Supplier;
import com.pj.store.service.SupplierManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/supplierManage")
public class SupplierManageController {
	@Autowired
	private SupplierManageService supplierManageService;

	private static final String SEARCH_BY_ID = "searchByID";
	private static final String SEARCH_BY_NAME = "searchByName";
	private static final String SEARCH_ALL = "searchAll";

	/**
	 * 通用的记录查询
	 *
	 * @param searchType 查询类型
	 * @param keyWord    查询关键字
	 * @return 返回所有符合条件的记录
	 */
	private Map<String, Object> query(String searchType, String keyWord){
		Map<String, Object> queryResult = null;

		switch (searchType) {
			case SEARCH_BY_ID:
				if (keyWord.length() != 0) {
					queryResult = supplierManageService.selectById(Integer.valueOf(keyWord));
				}
				break;
			case SEARCH_BY_NAME:
				queryResult = supplierManageService.selectByName(keyWord);
				break;
			case SEARCH_ALL:
				queryResult = supplierManageService.selectAll();
				break;
			default:
				// do other thing
				break;
		}

		return queryResult;
	}

	@RequestMapping("getSupplierList")
	@ResponseBody
	public Map<String, Object> getSupplierList(String searchType, String keyWord) {
		// 初始化 Response
//        Response responseContent = ResponseFactory.newInstance();

//        List<Supplier> rows = null;
//        long total = 0;
		Map<String, Object> queryResult = query(searchType, keyWord);
//		Map<String, Object> queryResult = supplierManageService.selectAll();

		// 结果转换
//        if (queryResult != null) {
//            rows = (List<Supplier>) queryResult.get("data");
//            total = (long) queryResult.get("total");
//        }

//        responseContent.setCustomerInfo("rows", rows);
//        responseContent.setResponseTotal(total);
		System.out.println(queryResult);
		return queryResult;//responseContent.generateResponse();

	}
	/**
	 * 添加一条供应商信息
	 *
	 * @param supplier 供应商信息
	 * @return 返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
	 */
	@RequestMapping(value = "addSupplier", method = RequestMethod.POST)
	public boolean addSupplier(@RequestBody Supplier supplier){

		System.out.println(supplier+"1231");
		// 初始化 Responsev
//		Response responseContent = responseUtil.newResponseInstance();

		// 添加记录
		boolean result = supplierManageService.addSupplier(supplier);// ? Response.RESPONSE_RESULT_SUCCESS : Response.RESPONSE_RESULT_ERROR;

//		// 设置 Response
//		responseContent.setResponseResult(result);
//		return responseContent.generateResponse();
		System.out.println(result);
		return result;
	}

	/**
	 * 更新供应商信息
	 *
	 * @param supplier 供应商信息
	 * @return 返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
	 */
	@RequestMapping(value = "updateSupplier", method = RequestMethod.POST)
	public
	@ResponseBody
	boolean updateSupplier(@RequestBody Supplier supplier) {

		System.out.println(supplier);
		// 初始化 Response
//		Response responseContent = responseUtil.newResponseInstance();

		// 更新
		boolean result = supplierManageService.updateSupplier(supplier);

		return result;
	}

	/**
	 * 删除供应商记录
	 *
	 * @param supplierID 供应商ID
	 * @return 返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
	 */
	@RequestMapping("deleteSupplier")
	@ResponseBody
	public boolean deleteSupplier(String supplierID) {

		System.out.println(supplierID);
		Integer supplierIDD = Integer.valueOf(supplierID);
		// 初始化 Response
//		Response responseContent = responseUtil.newResponseInstance();

		// 刪除
		boolean result = supplierManageService.deleteSupplier(supplierIDD) ;//? Response.RESPONSE_RESULT_SUCCESS : Response.RESPONSE_RESULT_ERROR;

		// 设置 Response
//		responseContent.setResponseResult(result);
		return result;//responseContent.generateResponse();
	}

}
