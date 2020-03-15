package com.pj.store.service.impl;


import com.pj.store.dao.GoodsMapper;
import com.pj.store.dao.StockInMapper;
import com.pj.store.dao.StockOutMapper;
import com.pj.store.dao.StorageMapper;
import com.pj.store.model.Goods;
import com.pj.store.service.GoodsManageService;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 货物信息管理Service 实现类
 *
 * @author Ken
 */
@Service
public class GoodsManageServiceImpl implements GoodsManageService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private StockInMapper stockInMapper;
    @Autowired
    private StockOutMapper stockOutMapper;


    @Override
    public List<Goods> selectAll() {
        List<Goods> goodsList = goodsMapper.selectAll();
        return goodsList;
    }
}
