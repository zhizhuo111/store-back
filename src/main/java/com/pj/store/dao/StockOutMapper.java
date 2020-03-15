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
}
