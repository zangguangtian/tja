package com.df.tja.service;

import java.util.List;

import com.df.framework.base.service.IBaseService;
import com.df.framework.exception.LogicalException;
import com.df.tja.domain.cust.CustSchemeMajorNode;
import com.df.tja.domain.cust.OcSchemeMajorTask;
import com.df.tja.domain.cust.OcSchemeStageMajor;

/**
 * 
 * <p>IMajorSchemeService</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：专业策划接口</p>
 * 
 * <p>2018年11月12日 上午11:09:12</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 *
 */
public interface IMajorSchemeService extends IBaseService {

    /**
     * 
     * <p>描述 : 通过专业ID，查找专业策划信息 </p>
     *
     * @param majorId
     * @return
     */
    OcSchemeStageMajor queryOcSchemeStageMajorById(String majorId);

    /**
     * 
     * <p>描述 : 查询专业策划中子项、任务列表 </p>
     *
     * @param majorId
     * @return
     */
    List<OcSchemeMajorTask> queryMajorTaskById(String majorId);

    /**
     * 
     * <p>描述 : 专业策划增加节点 </p>
     *
     * @param majorNode
     */
    void createSchemeMajorNode(CustSchemeMajorNode majorNode) throws LogicalException;
}
