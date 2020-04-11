package com.pj.store.service;


import com.pj.store.model.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 仓库信息管理 service
 *
 * @author Ken
 */
public interface RepositoryService {
    List<Repository> selectAll();

	List<Repository> selectById(Integer valueOf);

	List<Repository> selectByAddress(String keyword);

	Map<String, Object> selectUnassign();

	boolean addRepository(Repository repository);

	boolean updateRepository(Repository repository);

	boolean deleteRepository(Integer repositoryID);
}
