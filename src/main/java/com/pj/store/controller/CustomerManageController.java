package com.pj.store.controller;

import com.pj.store.model.Customer;
import com.pj.store.model.Supplier;
import com.pj.store.service.CustomerManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "/**/customerManage")
@Controller
public class CustomerManageController {
    @Autowired
    private CustomerManageService customerManageService;

    private static final String SEARCH_BY_ID = "searchByID";
    private static final String SEARCH_BY_NAME = "searchByName";
    private static final String SEARCH_ALL = "searchAll";

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
    /**
     * 搜索客户信息
     *
     * @param searchType 搜索类型
     * @param keyWord    搜索的关键字
     * @return 返回查询的结果，其中键值为 rows 的代表查询到的每一记录，若有分页则为分页大小的记录；键值为 total 代表查询到的符合要求的记录总条数
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("getCustomerLists")
    @ResponseBody
    public Map<String, Object> getCustomerLists(String searchType, String keyWord){

        Map<String, Object> queryResult = query(searchType, keyWord);

        return queryResult;
    }

    /**
     * 通用的结果查询方法
     *
     * @param searchType 查询方式
     * @param keyWord    查询关键字
     * @return 返回指定条件查询的结果
     */
    private Map<String, Object> query(String searchType, String keyWord) {
        Map<String, Object> queryResult = null;

        switch (searchType) {
            case SEARCH_BY_ID:
                if (keyWord != null)
                    queryResult = customerManageService.selectById(Integer.valueOf(keyWord));
                break;
            case SEARCH_BY_NAME:
                queryResult = customerManageService.selectByName(keyWord);
                break;
            case SEARCH_ALL:
                queryResult = customerManageService.selectAlls();
                break;
            default:
                // do other thing
                break;
        }
        return queryResult;
    }

    /**
     * 添加一条客户信息
     *
     * @param customer 客户信息
     * @return 返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
     */
    @RequestMapping(value = "addCustomer", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean addCustomer(@RequestBody Customer customer) {
        // 初始化 Response
//        Response responseContent = responseUtil.newResponseInstance();

        // 添加记录
        boolean result = customerManageService.addCustomer(customer);

        return result;
    }

    /**
     * 更新客户信息
     *
     * @param customer 客户信息
     * @return 返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
     */
    @RequestMapping(value = "updateCustomer", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean updateCustomer(@RequestBody Customer customer) {
        System.out.println(customer);

        // 更新
        boolean result = customerManageService.updateCustomer(customer) ;

        return result;
    }

    /**
     * 删除客户记录
     *
     * @param customerIDStr 客户ID
     * @return 返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
     */
    @RequestMapping(value = "deleteCustomer", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean deleteCustomer(@RequestParam("customerID") String customerIDStr) {
        boolean result = false;
        // 参数检查
        if (customerIDStr != null) {
            // 转换为 Integer
            Integer customerID = Integer.valueOf(customerIDStr);

            // 刪除
            result = customerManageService.deleteCustomer(customerID);
        }

        return result;
    }
}
