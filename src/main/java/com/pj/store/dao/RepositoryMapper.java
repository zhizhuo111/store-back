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

	List<Repository> selectUnassign();

	/**
	 * 选择指定 repository Address 的 repository 记录
	 * @param address 仓库地址
	 * @return 返回指定的Repository
	 */
	List<Repository> selectByAddress(String address);

	/**
	 * 插入一条新的 Repository 记录
	 * @param repository 仓库信息
	 */
	void insert(Repository repository);

	/**
	 * 更新 Repository 记录
	 * @param repository 仓库信息
	 */
	void update(Repository repository);

	/**
	 * 删除指定 Repository ID 的 Repository 记录
	 * @param repositoryID 仓库ID
	 */
	void deleteByID(Integer repositoryID);
}
