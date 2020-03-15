package com.pj.store.controller;

import com.pj.store.model.Repository;
import com.pj.store.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/repositoryManage")
public class RepositoryManageController {
    @Autowired
    RepositoryService repositoryService;
    /**
     * 查询 数据库中有哪些数据库
     * @return
     */
    @RequestMapping("getRepositoryList")
    @ResponseBody
    List<Repository> getRepositoryList() {
        List<Repository> list = repositoryService.selectAll();
        System.out.println(list);
        return list;
    }

}
