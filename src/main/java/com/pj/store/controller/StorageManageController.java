package com.pj.store.controller;

import com.pj.store.model.Storage;
import com.pj.store.service.StorageManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/storageManage")
public class StorageManageController {
    @Autowired
    private StorageManageService storageManageService;

    private static final String SEARCH_BY_GOODS_ID = "searchByGoodsID";
    private static final String SEARCH_BY_GOODS_NAME = "searchByGoodsName";
    private static final String SEARCH_BY_GOODS_TYPE = "searchByGoodsType";
    private static final String SEARCH_ALL = "searchAll";

    @RequestMapping("commodityInquiry")
    @ResponseBody
    public List<Storage> storageSearch(String searchType, String repositoryBelong, String  searchTypeInput) {
        List<Storage> queryResult = null;
        System.out.println(searchType);
        System.out.println(searchTypeInput);
        System.out.println("============");
        System.out.println(repositoryBelong);
        switch (searchType) {
            case SEARCH_ALL://查询所有
                if (repositoryBelong.length() != 0) {//根据选择特定仓库查询
                    System.out.println(repositoryBelong);
                    Integer repositoryID = Integer.valueOf(repositoryBelong);
                    System.out.println(repositoryID);
                    queryResult = storageManageService.selectAll(repositoryID);
                } else {
                    queryResult = storageManageService.selectAll();
                }
                break;
            case SEARCH_BY_GOODS_ID://根据货物ID查询
                Integer goodsID = null;
                if(searchTypeInput!=""){
                     goodsID = Integer.valueOf(searchTypeInput);
                }
                if (repositoryBelong.length() != 0) {//根据选择特定仓库查询
                    Integer repositoryID = Integer.valueOf(repositoryBelong);
                    System.out.println(goodsID);
                    System.out.println(repositoryID);
                    queryResult = storageManageService.selectByGoodsID(goodsID, repositoryID);
                } else {
                    queryResult = storageManageService.selectByGoodsID(goodsID);
                }
                break;
            case SEARCH_BY_GOODS_TYPE://根据货物类型查询
                String goodsType = "";
                goodsType = searchTypeInput;
                if (repositoryBelong.length() != 0) {//根据选择特定仓库查询
                    Integer repositoryID = Integer.valueOf(repositoryBelong);
                    System.out.println(goodsType);
                    System.out.println(repositoryID);
                    queryResult = storageManageService.selectByGoodsType(goodsType, repositoryID);
                } else {
                    queryResult = storageManageService.selectByGoodsType(goodsType);
                }
                break;
            case SEARCH_BY_GOODS_NAME://根据货物名称查询
                String goodsName = "";
                goodsName = searchTypeInput;
                if (repositoryBelong.length() != 0) {// 根 据选择特定仓库查询
                    Integer repositoryID = Integer.valueOf(repositoryBelong);
                    System.out.println(goodsName);
                    System.out.println(repositoryID);
                    queryResult = storageManageService.selectByGoodsName(goodsName, repositoryID);
                } else {
                    System.out.println(goodsName);
                    queryResult = storageManageService.selectByGoodsName(goodsName);
                }
                break;
            default:
                // do other thing
                break;
        }
        return queryResult;
    }

}



