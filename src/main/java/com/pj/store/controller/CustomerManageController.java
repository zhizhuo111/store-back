package com.pj.store.controller;

import com.pj.store.model.Customer;
import com.pj.store.model.Supplier;
import com.pj.store.service.CustomerManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "/**/customerManage")
@Controller
public class CustomerManageController {
    @Autowired
    private CustomerManageService customerManageService;

    /**
     * 货物出库，客户下拉查询
     * @return
     */
    @RequestMapping(value = "getCustomerList", method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getCustomerList(){
        List<Customer> queryResult = customerManageService.selectAll();
        System.out.println(queryResult);
        System.out.println("================");
        return queryResult;
    }
}
