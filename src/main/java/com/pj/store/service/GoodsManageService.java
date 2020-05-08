package com.pj.store.service;


import com.pj.store.model.Goods;

import java.util.List;
import java.util.Map;

/**
 * 货物信息管理 service
 *
 * @author Ken
 */
public interface GoodsManageService {
    List<Goods> selectAll();

    /**
     * 返回指定goods ID 的货物记录
     *
     * @param goodsId 货物ID
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    List<Goods> selectById(Integer goodsId);

    /**
     * 返回指定 goods name 的货物记录
     * 支持查询分页以及模糊查询
     *
     * @param goodsName 货物的名称
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    List<Goods> selectByName(String goodsName);

    /**
     * 添加货物记录
     *
     * @param goods 货物信息
     * @return 返回一个boolean值，值为true代表更新成功，否则代表失败
     */
    boolean addGoods(Goods goods);

    /**
     * 更新货物记录
     *
     * @param goods 供应商信息
     * @return 返回一个boolean值，值为true代表更新成功，否则代表失败
     */
    boolean updateGoods(Goods goods);

    /**
     * 删除货物记录
     *
     * @param goodsID 货物ID
     * @return 返回一个boolean值，值为true代表更新成功，否则代表失败
     */
    boolean deleteGoods(Integer goodsID);
}
