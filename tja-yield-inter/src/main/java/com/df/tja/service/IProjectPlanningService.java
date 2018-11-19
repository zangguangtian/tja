package com.df.tja.service;

import com.df.framework.base.service.IBaseService;
import com.df.framework.exception.LogicalException;
import com.df.tja.domain.cust.OcSchemeMode;

/**
 * <p>IProjectPlanningService </p>
 *
 * <p>描述： </p>
 *
 * <p>备注：</p>
 *
 * <p>2018-11-19 10:27</p>
 *
 * @author deng.jiayan
 * @version 1.0.0
 */
public interface IProjectPlanningService extends IBaseService {

    /**
     * <p>描述 : 项目策划保存</p>
     *
     * @param
     * @return
     */
    void submitProjectPlanning(OcSchemeMode ocSchemeMode) throws LogicalException;

}
