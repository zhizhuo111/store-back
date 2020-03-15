package com.pj.store.dao;

import com.pj.store.model.RolePermissionDO;

import java.util.List;

/**
 * 角色权限信息 Mapper
 *
 * @author ken
 * @since  2017/2/26.
 */
public interface RolePermissionMapper {

    List<RolePermissionDO> selectAll();
}
