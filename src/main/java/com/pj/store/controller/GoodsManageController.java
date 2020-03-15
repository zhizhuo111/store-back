package com.pj.store.controller;

import com.pj.store.model.Goods;
import com.pj.store.service.GoodsManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goodsManage")
public class GoodsManageController {
    @Autowired
    private GoodsManageService goodsManageService;
    @RequestMapping("getGoodsList")
    List<Goods> getGoodsList(){
        // 查询
        List<Goods> queryResult = goodsManageService.selectAll();

        return queryResult;
    }
}
