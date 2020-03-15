package com.pj.store.service.impl;

import com.pj.store.dao.*;
import com.pj.store.model.Goods;
import com.pj.store.model.Repository;
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
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private RepositoryMapper repositoryMapper;

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

    /**
     * 添加一条新的库存记录
     *
     * @param goodsID      指定的货物ID
     * @param repositoryID 指定的仓库ID
     * @param number       库存数量
     * @return 返回一个boolean值，值为true代表更新成功，否则代表失败
     */
    @Override
        public boolean addNewStorage(Integer goodsID, Integer repositoryID, long number) {
        boolean isAvailable = true;
        try {

            // validate
            Goods goods = goodsMapper.selectById(goodsID);
            Repository repository = repositoryMapper.selectByID(repositoryID);
            if (goods == null)
                isAvailable = false;
            if (repository == null)
                isAvailable = false;
            if (number < 0)
                isAvailable = false;
            List<Storage> storageList = storageMapper.selectByGoodsIDAndRepositoryID(goodsID, repositoryID);
            if (!(storageList != null && storageList.isEmpty()))
                isAvailable = false;

            if (isAvailable) {
                // insert
                Storage storage = new Storage();
                storage.setGoodsID(goodsID);
                storage.setRepositoryID(repositoryID);
                storage.setNumber(number);
                storageMapper.insert(storage);
            }

            return isAvailable;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAvailable;
    }

    /**
     * 更新一条库存记录
     *
     * @param goodsID      指定的货物ID
     * @param repositoryID 指定的仓库ID
     * @param number       更新的库存数量
     * @return 返回一个boolean值，值为true代表更新成功，否则代表失败
     */
    @Override
    public boolean updateStorage(Integer goodsID, Integer repositoryID, long number){
        boolean isUpdate = false;
        try {

            // validate
            List<Storage> storageList = storageMapper.selectByGoodsIDAndRepositoryID(goodsID, repositoryID);
            if (storageList != null && !storageList.isEmpty()) {
                if (number >= 0) {
                    // update
                    Storage storage = storageList.get(0);
                    storage.setNumber(number);
                    storageMapper.update(storage);
                    isUpdate = true;
                }
            }

            return isUpdate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUpdate;
    }


    /**
     * 为指定的货物库存记录增加指定数目
     *
     * @param goodsID      货物ID
     * @param repositoryID 仓库ID
     * @param number       增加的数量
     * @return 返回一个 boolean 值，若值为true表示数目增加成功，否则表示增加失败
     */
    @Override
    public boolean storageIncrease(Integer goodsID, Integer repositoryID, long number){

        // 检查货物库存增加数目的有效性
        if (number < 0)
            return false;

        synchronized (this) {
            // 检查对应的库存记录是否存在
            Storage storage = getStorage(goodsID, repositoryID);
            if (storage != null) {
                long newStorage = storage.getNumber() + number;
                updateStorage(goodsID, repositoryID, newStorage);
            } else {
                addNewStorage(goodsID, repositoryID, number);
            }
        }
        return true;
    }

    /**
     * 获取指定货物ID，仓库ID对应的库存记录
     *
     * @param goodsID      货物ID
     * @param repositoryID 仓库ID
     * @return 若存在则返回对应的记录，否则返回null
     */
    private Storage getStorage(Integer goodsID, Integer repositoryID) {
        Storage storage = null;
        List<Storage> storageList = storageMapper.selectByGoodsIDAndRepositoryID(goodsID, repositoryID);
        if (!storageList.isEmpty())
            storage = storageList.get(0);
        return storage;
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
