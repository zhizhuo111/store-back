package com.pj.store.dao;

import com.pj.store.model.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GoodsMapper {
    /**
     * 选择指定 id 的 Goods
     * @param id 货物的ID
     * @return 返回执行ID对应的Goods
     */
    Goods selectById(Integer goodsID);
//    查询货物列表
    List<Goods> selectAll();
}
