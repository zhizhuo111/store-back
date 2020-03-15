package com.pj.store.controller;

import com.pj.store.model.Supplier;
import com.pj.store.service.SupplierManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/supplierManage")
public class SupplierManageController {
    @Autowired
    private SupplierManageService supplierManageService;
    @RequestMapping("getSupplierList")
    @ResponseBody
    public Map<String, Object> getSupplierList(){
        // 初始化 Response
//        Response responseContent = ResponseFactory.newInstance();

//        List<Supplier> rows = null;
//        long total = 0;

        Map<String, Object> queryResult = supplierManageService.selectAll();

        // 结果转换
//        if (queryResult != null) {
//            rows = (List<Supplier>) queryResult.get("data");
//            total = (long) queryResult.get("total");
//        }

//        responseContent.setCustomerInfo("rows", rows);
//        responseContent.setResponseTotal(total);
        return queryResult;//responseContent.generateResponse();

    }
}
