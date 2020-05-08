package com.pj.store.service.impl;


import com.pj.store.dao.GoodsMapper;
import com.pj.store.dao.StockInMapper;
import com.pj.store.dao.StockOutMapper;
import com.pj.store.dao.StorageMapper;
import com.pj.store.model.Goods;
import com.pj.store.model.StockInDO;
import com.pj.store.model.StockOutDO;
import com.pj.store.model.Storage;
import com.pj.store.service.GoodsManageService;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private StorageMapper storageMapper;

    @Override
    public List<Goods> selectAll() {
        List<Goods> goodsList = goodsMapper.selectAll();
        return goodsList;
    }

    /**
     * 返回指定goods ID 的货物记录
     *
     * @param goodsId 货物ID
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    @Override
    public List<Goods> selectById(Integer goodsId) {
        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<>();
        List<Goods> goodsList = new ArrayList<>();
        long total = 0;

        // 查询
        Goods goods;
            goods = goodsMapper.selectById(goodsId);
            goodsList.add(goods);

        return goodsList;
    }

    /**
     * 返回指定 goods name 的货物记录 支持查询分页以及模糊查询
     *
     * @param goodsName 货物的名称
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    @Override
    public List<Goods> selectByName(String goodsName) {
        // 初始化结果集
        List<Goods> goodsList;
        // query
                goodsList = goodsMapper.selectApproximateByName(goodsName);
        return goodsList;
    }

    /**
     * 添加货物记录
     *
     * @param goods 货物信息
     * @return 返回一个boolean值，值为true代表更新成功，否则代表失败
     */
    @Override
    public boolean addGoods(Goods goods) {
        // 插入新的记录
        if (goods != null) {
            goodsMapper.insert(goods);
            return true;
        }
        return false;
    }

    /**
     * 更新货物记录
     *
     * @param goods 货物信息
     * @return 返回一个boolean值，值为true代表更新成功，否则代表失败
     */
    @Override
    public boolean updateGoods(Goods goods) {
        // 更新记录
        if (goods != null) {
            // 检验
            goodsMapper.update(goods);
            return true;
        }
        return false;
    }

    /**
     * 删除货物记录
     *
     * @param goodsId 货物ID
     * @return 返回一个boolean值，值为true代表更新成功，否则代表失败
     */
    @Override
    public boolean deleteGoods(Integer goodsId) {
        // 检查该货物是否有入库信息
        List<StockInDO> stockInDORecord = stockInMapper.selectByGoodID(goodsId);
        if (stockInDORecord != null && !stockInDORecord.isEmpty()) {
            return false;
        }
        // 检查该货物是否有出库信息
        List<StockOutDO> stockOutDORecord = stockOutMapper.selectByGoodId(goodsId);
        if (stockOutDORecord != null && !stockOutDORecord.isEmpty()) {
            return false;
        }
        // 检查该货物是否有存储信息
        List<Storage> storageRecord = storageMapper.selectByGoodsIDAndRepositoryID(goodsId, null);
        if (storageRecord != null && !storageRecord.isEmpty()) {
            return false;
        }
        // 删除货物记录
        goodsMapper.deleteById(goodsId);
        return true;
    }
}
