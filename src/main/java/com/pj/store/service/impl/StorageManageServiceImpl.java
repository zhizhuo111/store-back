package com.pj.store.service.impl;

import com.pj.store.dao.StorageMapper;
import com.pj.store.model.Storage;
import com.pj.store.service.StorageManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * 库存查询
 */
@Service
public class StorageManageServiceImpl implements StorageManageService {
    @Autowired
    private StorageMapper storageMapper;

    /**
     * 查询 特定仓库 的所有库存信息
     *
     * @param repositoryID
     * @return
     */
    @Override
    public List<Storage> selectAll(Integer repositoryID) {
            List<Storage> result = storageMapper.selectAllAndRepositoryID(repositoryID);
        System.out.println(result);
        return result;
    }

    /**
     * 查询 所有库存信息
     *
     * @return
     */
    @Override
    public List<Storage> selectAll() {
        List<Storage> result = storageMapper.selectAllAndRepositoryID(null);
        System.out.println(result);
        return result;
    }

    /**
     * 根据货物ID 和 仓库查询
     * @param goodsID
     * @param repository
     * @return
     */
    @Override
    public List<Storage> selectByGoodsID(Integer goodsID, Integer repository) {
        return storageMapper.selectByGoodsIDAndRepositoryID(goodsID, repository);
    }

    /**
     *只根据货物ID进行查询
     * @param goodsID
     * @return
     */
    @Override
    public List<Storage> selectByGoodsID(Integer goodsID) {
        return selectByGoodsID(goodsID, null);
    }

    @Override
    public List<Storage> selectByGoodsName(String goodsName, Integer repositoryID) {
        return storageMapper.selectByGoodsNameAndRepositoryID(goodsName, repositoryID);
    }

    @Override
    public List<Storage> selectByGoodsName(String goodsName) {
        return selectByGoodsName(goodsName,null);
    }

    /**
     * 根据货物类型 和 仓库查询
     * @param goodsType
     * @param repositoryID
     * @return
     */
    @Override
    public List<Storage> selectByGoodsType(String goodsType, Integer repositoryID){
        return storageMapper.selectByGoodsTypeAndRepositoryID(goodsType, repositoryID);
    }

    /**
     *  根据货物类型进行查询
     * @param goodsType
     * @return
     */
    @Override
    public List<Storage> selectByGoodsType(String goodsType){
        return selectByGoodsType(goodsType, null);
    }

    @Override
    public boolean addNewStorage(Integer goodsID, Integer repositoryID, long number) {
        return false;
    }

    @Override
    public boolean updateStorage(Integer goodsID, Integer repositoryID, long number) {
        return false;
    }

    @Override
    public boolean storageIncrease(Integer goodsID, Integer repositoryID, long number) {
        return false;
    }

    @Override
    public boolean storageDecrease(Integer goodsID, Integer repositoryID, long number) {
        return false;
    }

    @Override
    public boolean deleteStorage(Integer goodsID, Integer repositoryID) {
        return false;
    }

    @Override
    public List<Storage> importStorage(MultipartFile file) {
        return null;
    }

    @Override
    public File exportStorage(List<Storage> storages) {
        return null;
    }
}
