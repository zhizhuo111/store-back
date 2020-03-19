package com.pj.store.service.impl;

import com.pj.store.dao.RepositoryAdminMapper;
import com.pj.store.dao.RepositoryMapper;
import com.pj.store.model.RepositoryAdmin;
import com.pj.store.service.RepositoryAdminManageService;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 仓库管理员管理 service 实现类
 *
 * @author Ken
 */
@Service
public class RepositoryAdminManageServiceImpl implements RepositoryAdminManageService {

    @Autowired
    private RepositoryAdminMapper repositoryAdminMapper;

    @Override
    public List<RepositoryAdmin> selectAll() {
//        Map<String, Object> resultSet = new HashMap<>();
//        List<RepositoryAdmin> repositoryAdmins;
//        long total = 0;
//        boolean isPagination = true;
//
//        // validate
//        if (offset < 0 || limit < 0)
//            isPagination = false;

        // query
//        try {
//            if (isPagination) {
//                PageHelper.offsetPage(offset, limit);
//                repositoryAdmins = repositoryAdminMapper.selectAll();
//                if (repositoryAdmins != null) {
//                    PageInfo<RepositoryAdmin> pageInfo = new PageInfo<>(repositoryAdmins);
//                    total = pageInfo.getTotal();
//                } else
//                    repositoryAdmins = new ArrayList<>();
//            } else {
//                repositoryAdmins = repositoryAdminMapper.selectAll();
//                if (repositoryAdmins != null)
//                    total = repositoryAdmins.size();
//                else
//                    repositoryAdmins = new ArrayList<>();
//            }
//        } catch (PersistenceException e) {
//            throw new RepositoryAdminManageServiceException(e);
//        }

//        resultSet.put("data", repositoryAdmins);
//        resultSet.put("total", total);
        List<RepositoryAdmin> repositoryAdmins = repositoryAdminMapper.selectAll();
        return repositoryAdmins;
    }
    /**
     * 返回指定repository id 的仓库管理员记录
     *
     * @param repositoryAdminID 仓库管理员ID
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    @Override
    public List<RepositoryAdmin> selectByID(Integer repositoryAdminID) {
        List<RepositoryAdmin>  repositoryAdmins =  new ArrayList<>();
        RepositoryAdmin repositoryAdmin  = repositoryAdminMapper.selectByID(repositoryAdminID);
        repositoryAdmins.add(repositoryAdmin);
        return repositoryAdmins;
    }
    /**
     * 返回指定 repository address 的仓库管理员记录 支持查询分页以及模糊查询
     *
     * @param name   仓库管理员的名称
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    @Override
    public List<RepositoryAdmin> selectByName(String name) {
        List<RepositoryAdmin> repositoryAdmins = repositoryAdminMapper.selectByName(name);
        return repositoryAdmins;
    }

    @Override
    public List<RepositoryAdmin> selectByRepositoryID(Integer repositoryID) {
        List<RepositoryAdmin> repositoryAdmins = new ArrayList<>();
        RepositoryAdmin repositoryAdmin = repositoryAdminMapper.selectByRepositoryID(repositoryID);
        repositoryAdmins.add(repositoryAdmin);
        return repositoryAdmins;
    }
}
