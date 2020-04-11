package com.pj.store.service.impl;

import com.pj.store.dao.*;
import com.pj.store.model.*;
import com.pj.store.service.RepositoryService;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 仓库信息管理 service 实现类
 *
 * @author Ken
 */
@Service
public class RepositoryManageServiceImpl implements RepositoryService {

    @Autowired
    private RepositoryMapper repositoryMapper;
    @Autowired
    private StockInMapper stockInMapper;
    @Autowired
    private StockOutMapper stockOutMapper;
    @Autowired
    private StorageMapper storageMapper;
    @Autowired
    private RepositoryAdminMapper repositoryAdminMapper;


    @Override
    public List<Repository> selectAll() {
        return repositoryMapper.selectAll();
    }

    /**
     * 返回指定 repository ID 的仓库记录
     *
     * @param repositoryId 仓库ID
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    @Override
    public List<Repository> selectById(Integer repositoryId) {
        // 初始化結果集
        List<Repository> repositories = new ArrayList<>();
        long total = 0;

        // 查詢
        Repository repository;
            repository = repositoryMapper.selectByID(repositoryId);

        if (repository != null) {
            repositories.add(repository);
            total = 1;
        }
        return repositories;
    }

    /**
     * 返回指定 repository address 的仓库记录 支持查询分页以及模糊查询
     * @param address 仓库的地址
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    @Override
    public List<Repository> selectByAddress(String address) {
        // 初始化結果集
        List<Repository> repositories;
        long total = 0;
        boolean isPagination = true;

        // query
        repositories = repositoryMapper.selectByAddress(address);
        return repositories;
    }

    /**
     * 查询所有未指派仓库管理员的仓库记录
     *
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    @Override
    public Map<String, Object> selectUnassign() {
        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<>();
        List<Repository> repositories;
        long total = 0;

        // 查询
            repositories = repositoryMapper.selectUnassign();

        return resultSet;
    }

    /**
     * 检查仓库信息是否满足
     *
     * @param repository 仓库信息
     * @return 若仓库信息满足要求则返回true，否则返回false
     */
    private boolean repositoryCheck(Repository repository) {
        return repository.getAddress() != null && repository.getStatus() != null && repository.getArea() != null;
    }

    /**
     * 添加仓库记录
     *
     * @param repository 仓库信息
     * @return 返回一个boolean值，值为true代表更新成功，否则代表失败
     */
    @Override
    public boolean addRepository(Repository repository) {

        // 插入一条新的记录
        if (repository != null) {
                // 有效性验证
                if (repositoryCheck(repository)) {
                    repositoryMapper.insert(repository);
                }
                if (repository.getId() != null) {
                    return true;
                }
        }
        return false;
    }

    /**
     * 更新仓库记录
     *
     * @param repository 仓库信息
     * @return 返回一个boolean值，值为true代表更新成功，否则代表失败
     */
    @Override
    public boolean updateRepository(Repository repository){

        // 更新仓库记录
        if (repository != null) {
            // 有效性验证
                if (repositoryCheck(repository)) {
                    if (repository.getId() != null) {
                        repositoryMapper.update(repository);
                        return true;
                    }
                }
        }
        return false;
    }

    /**
     * 删除仓库记录
     *
     * @param repositoryId 仓库ID
     * @return 返回一个boolean值，值为true代表更新成功，否则代表失败
     */
    @Override
    public boolean deleteRepository(Integer repositoryId) {

            // 检查是否存在出库记录
            List<StockOutDO> stockOutDOList = stockOutMapper.selectByRepositoryID(repositoryId);
            if (stockOutDOList != null && !stockOutDOList.isEmpty()) {
                return false;
            }
            // 检查是否存在入库记录
            List<StockInDO> stockInDOList = stockInMapper.selectByRepositoryID(repositoryId);
            if (stockInDOList != null && !stockInDOList.isEmpty()) {
                return false;
            }
            // 检查是否存在库存记录
            List<Storage> storageRecords = storageMapper.selectAllAndRepositoryID(repositoryId);
            if (storageRecords != null && !storageRecords.isEmpty()) {
                return false;
            }
            // 检查是否已指派仓库管理员
            RepositoryAdmin repositoryAdmin = repositoryAdminMapper.selectByRepositoryID(repositoryId);
            if (repositoryAdmin != null) {
                return false;
            }
            // 删除记录
            repositoryMapper.deleteByID(repositoryId);

            return true;
    }

}
