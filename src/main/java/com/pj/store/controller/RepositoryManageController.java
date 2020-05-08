package com.pj.store.controller;

import com.pj.store.model.Repository;
import com.pj.store.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/repositoryManage")
public class RepositoryManageController {
    @Autowired
    RepositoryService repositoryService;
    private static final String SEARCH_BY_ID = "searchByID";
    private static final String SEARCH_BY_ADDRESS = "searchByAddress";
    private static final String SEARCH_ALL = "searchAll";

    /**
     * 查询 数据库中有哪些数据
     * @return
     */
    @RequestMapping("getRepositoryList")
    @ResponseBody
    List<Repository> getRepositoryList() {
        List<Repository> list = repositoryService.selectAll();

        System.out.println(list+"sks");
        return list;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "getRepositoryList", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Repository> getRepositoryList(@RequestParam("searchType") String searchType, @RequestParam("keyWord") String keyWord) {
        // 查询
        System.out.println(searchType);
        System.out.println(keyWord);
        List<Repository> queryResult = query(searchType, keyWord);
        return queryResult;
    }

    /**
     * 通用的记录查询
     *
     * @param searchType 查询方式
     * @param keyword    查询关键字
     * @return 返回所有符合条件的查询结果
     */
    private List<Repository> query(String searchType, String keyword) {
        List<Repository> queryResult = null;

        switch (searchType) {
            case SEARCH_BY_ID:
                if (keyword != null) {
                    queryResult = repositoryService.selectById(Integer.valueOf(keyword));
                }
                break;
            case SEARCH_BY_ADDRESS:
                queryResult = repositoryService.selectByAddress(keyword);
                break;
            case SEARCH_ALL:
                queryResult = repositoryService.selectAll();
                break;
            default:
                // do other thing
                break;
        }

        return queryResult;
    }

    /**
     * 查询所有未指派管理员的仓库
     *
     * @return 返回一个 map，其中key=data表示查询的记录，key=total表示记录的条数
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "getUnassignRepository", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getUnassignRepository() {
        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<>();
        List<Repository> data;
        long total = 0;

        // 查询
        Map<String, Object> queryResult = repositoryService.selectUnassign();
        if (queryResult != null) {
            data = (List<Repository>) queryResult.get("data");
            total = (long) queryResult.get("total");
        } else {
            data = new ArrayList<>();
        }
        resultSet.put("data", data);
        resultSet.put("total", total);
        return resultSet;
    }

    /**
     * 添加一条仓库信息
     *
     * @param repository 仓库信息
     * @return 返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
     */
    @RequestMapping(value = "addRepository", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean addRepository(@RequestBody Repository repository) {
        System.out.println(repository);
        // 添加记录
        boolean result = repositoryService.addRepository(repository);
        System.out.println(result);
        // 设置 Response
        return result;
    }

    /**
     * 查询指定 ID 的仓库信息
     *
     * @param repositoryID 仓库ID
     * @return 返回一个map，其中：key 为 result 的值为操作的结果，包括：success 与 error；key 为 data
     * 的值为仓库信息
     */
    @RequestMapping(value = "getRepositoryInfo", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getRepositoryInfo(@RequestParam("repositoryID") Integer repositoryID) {

        // 查询
        Repository repository = null;
        Map<String, Object> queryResult = (Map<String, Object>) repositoryService.selectById(repositoryID);
        if (queryResult != null) {
            repository = (Repository) queryResult.get("data");
        }

        // 设置 Response
        return queryResult;
    }

    /**
     * 更新仓库信息
     *
     * @param repository 仓库信息
     * @return 返回一个map，其中：key 为 result 的值为操作的结果，包括：success 与 error；key 为 data
     * 的值为仓库信息
     */
    @RequestMapping(value = "updateRepository", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean updateRepository(@RequestBody Repository repository) {

        // 更新
        boolean result = repositoryService.updateRepository(repository);

        // 设置 Response
        return result;
    }

    /**
     * 删除指定 ID 的仓库信息
     *
     * @param repositoryID 仓库ID
     * @return 返回一个map，其中：key 为 result 的值为操作的结果，包括：success 与 error；key 为 data
     * 的值为仓库信息
     */
    @RequestMapping(value = "deleteRepository", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean deleteRepository(@RequestParam("repositoryID") Integer repositoryID) {
        System.out.println(repositoryID);
        // 删除记录
        boolean result = repositoryService.deleteRepository(repositoryID);

        return result;
    }
}
