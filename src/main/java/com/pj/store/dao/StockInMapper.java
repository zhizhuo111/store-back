package com.pj.store.dao;

import com.pj.store.model.StockInDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface StockInMapper {
    /**
     * 选择指定仓库ID以及指定日期范围内的入库记录
     *
     * @param repositoryID 指定的仓库ID
     * @param startDate    记录的起始日期
     * @param endDate      记录的结束日期
     * @return 返回所有符合要求的入库记录
     */
    List<StockInDO> selectByRepositoryIDAndDate(@Param("repositoryID") Integer repositoryID,
                                                @Param("startDate") Date startDate,
                                                @Param("endDate") Date endDate);

    void insert(StockInDO stockInDO);

	/**
	 * 选择指定供应商ID相关的入库记录
	 *
	 * @param supplierID 指定的供应商ID
	 * @return 返回指定供应商相关的入库记录
	 */
	List<StockInDO> selectBySupplierId(Integer supplierID);

	List<StockInDO> selectByGoodID(Integer goodsId);

	/**
	 * 选择指定仓库ID相关的入库记录
	 *
	 * @param repositoryID 指定的仓库ID
	 * @return 返回指定仓库相关的入库记录
	 */
	List<StockInDO> selectByRepositoryID(Integer repositoryID);
}
