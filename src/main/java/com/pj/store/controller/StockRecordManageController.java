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
                    @RequestParam("number") String numberStr){
        System.out.println(supplierIDStr);
        System.out.println(goodsIDStr);
        System.out.println(repositoryIDStr);
        System.out.println(numberStr);
        if(supplierIDStr == "" || goodsIDStr == "" || repositoryIDStr == "" || numberStr == ""){
            return false;
        }
//        // 初始化 Response
//        Response responseContent = ResponseFactory.newInstance();
//        String result = Response.RESPONSE_RESULT_ERROR;
//        boolean authorizeCheck = true;
//        boolean argumentCheck = true;
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
//
//        // 获取session中的信息
//        Subject currentUser = SecurityUtils.getSubject();
//        Session session = currentUser.getSession();
//        UserInfoDTO userInfo = (UserInfoDTO) session.getAttribute("userInfo");
//        String personInCharge = userInfo == null ? "none" : userInfo.getUserName();
//        Integer repositoryIDBelong = userInfo == null ? -1 : userInfo.getRepositoryBelong();
//
//        // 设置非管理员请求的仓库ID
//        if (!currentUser.hasRole("systemAdmin")) {
//            if (repositoryIDBelong < 0) {
//                authorizeCheck = false;
//                responseContent.setResponseMsg("You are not authorized");
//            } else {
//                repositoryID = repositoryIDBelong;
//            }
//        }
//
//        // 执行 Service
//        if (authorizeCheck && argumentCheck) {
//            if (stockRecordManageService.stockInOperation(supplierID, goodsID, repositoryID, number, personInCharge)) {
//                result = Response.RESPONSE_RESULT_SUCCESS;
//            }
//        }
//
//        // 设置 Response
//        responseContent.setResponseResult(result);
//        return responseContent.generateResponse();
        boolean result = stockRecordManageService.stockInOperation(supplierID, goodsID, repositoryID, number);

        return result;
    }
    @Autowired
    StockRecordManageService stockRecordManageService;

    /**
     *
     * @param searchType 查詢出/入库记录
     * @param repositoryIDStr 查询对应的仓库
     * @return
     */
    @RequestMapping("searchStockRecord")
    @ResponseBody
    Map<String, Object> getStockRecord(String searchType, String repositoryIDStr, String beginDate, String endDate){
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
        return  queryResult;
    }

}
