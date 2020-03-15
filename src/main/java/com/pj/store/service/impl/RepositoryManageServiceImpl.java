package com.pj.store.service.impl;

import com.pj.store.dao.*;
import com.pj.store.model.Repository;
import com.pj.store.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    @Override
    public List<Repository> selectAll() {
        return repositoryMapper.selectAll();
    }
}
