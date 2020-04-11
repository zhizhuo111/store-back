package com.pj.store.controller;

import com.pj.store.model.Goods;
import com.pj.store.model.Supplier;
import com.pj.store.service.GoodsManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goodsManage")
public class GoodsManageController {
    @Autowired
    private GoodsManageService goodsManageService;

    private static final String SEARCH_BY_ID = "searchByID";
    private static final String SEARCH_BY_NAME = "searchByName";
    private static final String SEARCH_ALL = "searchAll";

    @RequestMapping("getGoodsList")
    List<Goods> getGoodsList(){
        // 查询
        List<Goods> queryResult = goodsManageService.selectAll();

        return queryResult;
    }
    /**
     * 搜索货物信息
     *
     * @param searchType 搜索类型
     * @param keyWord    搜索的关键字
     * @return 返回所有符合要求的记录
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "getGoodsList", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Goods> getGoodsList(@RequestParam("searchType") String searchType, @RequestParam("keyWord") String keyWord) {
        // 初始化 Response
        System.out.println(searchType);
        System.out.println(keyWord);
        // 查询
        List<Goods> queryResult = query(searchType, keyWord);


        // 设置 Response
        return queryResult;
    }

    /**
     * 通用的记录查询
     *
     * @param searchType 查询类型
     * @param keyWord    查询关键字
     * @return 返回一个 Map ，包含所有符合要求的查询结果，以及记录的条数
     */
    private List<Goods> query(String searchType, String keyWord) {
        List<Goods> queryResult = null;

        switch (searchType) {
            case SEARCH_BY_ID:
                if (keyWord != null) {
                    queryResult = goodsManageService.selectById(Integer.valueOf(keyWord));
                }
                break;
            case SEARCH_BY_NAME:
                queryResult = goodsManageService.selectByName(keyWord);
                break;
            case SEARCH_ALL:
                queryResult = goodsManageService.selectAll();
                break;
            default:
                // do other thing
                break;
        }

        return queryResult;
    }

    /**
     * 添加一条货物信息
     *
     * @param goods 货物信息
     * @return 返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
     */
    @RequestMapping(value = "addGoods", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean addGoods(@RequestBody Goods goods) {
        System.out.println(goods);
        // 添加记录
        boolean result = goodsManageService.addGoods(goods) ;


        return result;
    }

    /**
     * 更新货物信息
     *
     * @param goods 货物信息
     * @return 返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
     */
    @RequestMapping(value = "updateGoods", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean updateGoods(@RequestBody Goods goods) {

        // 更新
        boolean result = goodsManageService.updateGoods(goods) ;

        // 设置 Response
        return result;
    }

    /**
     * 删除货物记录
     *
     * @param goodsID 货物ID
     * @return 返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
     */
    @RequestMapping(value = "deleteGoods", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean deleteGoods(@RequestParam("goodsID") Integer goodsID) {

        // 删除
        boolean result = goodsManageService.deleteGoods(goodsID);

        // 设置 Response
        return result;
    }

}
