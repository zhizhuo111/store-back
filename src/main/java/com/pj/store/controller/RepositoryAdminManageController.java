package com.pj.store.controller;

import com.pj.store.model.RepositoryAdmin;
import com.pj.store.service.RepositoryAdminManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/repositoryAdminManage")
public class RepositoryAdminManageController {
    @Autowired
    RepositoryAdminManageService repositoryAdminManageService;
    // 查询类型
    private static final String SEARCH_BY_ID = "searchByID";
    private static final String SEARCH_BY_NAME = "searchByName";
    private static final String SEARCH_BY_REPOSITORY_ID = "searchByRepositoryID";
    private static final String SEARCH_ALL = "searchAll";
    /**
     * 通用记录查询
     *
     * @param keyWord    查询关键字
     * @param searchType 查询类型
     * @return 返回所有符合条件的记录
     */
    private List<RepositoryAdmin> query(String keyWord, String searchType ){
        List<RepositoryAdmin> queryResult = null;

        // query
        switch (searchType) {
            case SEARCH_ALL:
                queryResult = repositoryAdminManageService.selectAll();
                break;
            case SEARCH_BY_ID:
                if (keyWord != "")
                    queryResult = repositoryAdminManageService.selectByID(Integer.valueOf(keyWord));
                break;
            case SEARCH_BY_NAME:
                queryResult = repositoryAdminManageService.selectByName(keyWord);
                break;
            case SEARCH_BY_REPOSITORY_ID:
                if (keyWord != "")
                    queryResult = repositoryAdminManageService.selectByRepositoryID(Integer.valueOf(keyWord));
                break;
            default:
                // do other things
                break;
        }

        return queryResult;
    }

    /**
     * 查询仓库管理员信息
     *
     * @param searchType 查询类型
     * @param keyWord    查询关键字
     * @return 返回一个Map，其中key=rows，表示查询出来的记录；key=total，表示记录的总条数
     */
    @RequestMapping("getRepositoryAdminList")
    @ResponseBody
    public  List<RepositoryAdmin> getRepositoryAdmin(String searchType, String keyWord){
        // 初始化 Response
//        Response responseContent = responseUtil.newResponseInstance();

//        List<RepositoryAdmin> rows = null;
//        long total = 0;

        // 查询
        List<RepositoryAdmin> queryResult = query(keyWord, searchType);

//        if (queryResult != null) {
//            rows = (List<RepositoryAdmin>) queryResult.get("data");
//            total = (long) queryResult.get("total");
//        }

        // 设置 Response
//        responseContent.setCustomerInfo("rows", rows);
//        responseContent.setResponseTotal(total);
        return queryResult;
    }
}
