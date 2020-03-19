package com.pj.store.service;


import com.pj.store.model.RepositoryAdmin;

import java.util.List;

/**
 * 仓库管理员管理 service
 *
 * @author Ken
 */
public interface RepositoryAdminManageService {

    /**
     * 查询仓库管理员的所有信息
     * @return
     */
    List<RepositoryAdmin> selectAll();
    /**
     * 返回指定repository id 的仓库管理员记录
     *
     * @param repositoryAdminID 仓库管理员ID
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    List<RepositoryAdmin> selectByID(Integer repositoryAdminID);
    /**
     * 返回指定 repository Name 的仓库管理员记录
     * 支持模糊查询
     *
     * @param name 仓库管理员名称
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    List<RepositoryAdmin> selectByName(String name);
    /**
     * 返回所属指定 repositoryID 的仓库管理员信息
     *
     * @param repositoryID 仓库ID 其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     * @return 返回一个Map，
     */
    List<RepositoryAdmin> selectByRepositoryID(Integer repositoryID);
}
