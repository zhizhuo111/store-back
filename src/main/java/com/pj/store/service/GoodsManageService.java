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

}
