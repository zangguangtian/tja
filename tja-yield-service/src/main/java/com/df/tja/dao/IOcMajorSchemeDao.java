package com.df.tja.dao;

import java.util.List;

import com.df.framework.base.dao.IBaseDao;
import com.df.tja.domain.cust.OcSchemeMajorTask;
import com.df.tja.domain.cust.OcSchemeStageMajor;

public interface IOcMajorSchemeDao extends IBaseDao {

    /**
     * 
     * <p>描述 : 通过专业ID，查找专业策划信息  </p>
     *
     * @param majorId
     * @return
     */
    OcSchemeStageMajor selectOcSchemeStageMajorById(String majorId);

    /**
     * 
     * <p>描述 : </p>
     *
     * @param majorId
     * @return
     */
    List<OcSchemeMajorTask> selectMajorTaskById(String majorId);

    /**
     * 
     * <p>描述 : 更新子记录的tree_path </p>
     *
     * @param parentId
     * @param sort 从第几个排序号开始更新子记录的tree_path
     */
    void updateMajorTreePath(String parentId, Integer sort);
}
