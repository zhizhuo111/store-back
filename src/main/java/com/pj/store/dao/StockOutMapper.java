package com.pj.store.dao;

import com.pj.store.model.StockOutDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface StockOutMapper {

    List<StockOutDO> selectByRepositoryIDAndDate(Integer repositoryID, Date startDate, Date endDate);

    /**
     * 进行出库操作
     * @param stockOutDO
     */
    void insert(StockOutDO stockOutDO);

	/**
	 * 选择指定客户ID相关的出库记录
	 *
	 * @param customerId 指定的客户ID
	 * @return 返回指定客户相关的出库记录
	 */
	List<StockOutDO> selectByCustomerId(Integer customerId);

	List<StockOutDO> selectByGoodId(Integer goodsId);

	/**
	 * 选择指定仓库ID关联的出库记录
	 *
	 * @param repositoryID 指定的仓库ID
	 * @return 返回指定仓库ID相关的出库记录
	 */
	List<StockOutDO> selectByRepositoryID(Integer repositoryID);
}
