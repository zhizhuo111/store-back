package com.pj.store.dao;

import org.apache.ibatis.annotations.Mapper;
import com.pj.store.model.Repository;

import java.util.List;

@org.springframework.stereotype.Repository

@Mapper
public interface RepositoryMapper {

    List<Repository> selectAll();
    /**
     * 选择指定 Repository ID 的 Repository 记录
     * @param repositoryID 仓库ID
     * @return 返回指定的Repository
     */
    Repository selectByID(Integer repositoryID);
}
