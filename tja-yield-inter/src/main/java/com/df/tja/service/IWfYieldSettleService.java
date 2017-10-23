/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.service
 *
 * IWfYieldSettleService.java
 * 
 * 2017年10月19日-上午10:13:40
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.service;

import java.util.Map;

import com.df.activiti.domain.ProcessArgs;
import com.df.framework.base.service.IBaseService;
import com.df.framework.exception.LogicalException;
import com.df.tja.domain.WfYieldSettle;
import com.df.tja.domain.cust.WfYieldSettleModel;

/**
 * <p>IWfYieldSettleService</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月19日 上午10:13:40</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */

public interface IWfYieldSettleService extends IBaseService {

    /**
     * <p>描述 : </p>
     *
     * @param inParams
     * @param outParams
     */
    void queryYieldSettle(Map<String, Object> inParams, Map<String, Object> outParams) throws RuntimeException;

    /**
     * <p>描述 : </p>
     *
     * @param yieldSettle
     * @param processArgs
     * @param settleModel
     */
    void addOrModifyYieldSettle(WfYieldSettle yieldSettle, ProcessArgs processArgs, WfYieldSettleModel settleModel)
        throws RuntimeException;

    /**
     * <p>描述 : </p>
     *
     * @param modelMap
     * @param id
     */
    void queryYieldSettleForView(Map<String, Object> modelMap, String id) throws RuntimeException;

    /**
     * <p>描述 : </p>
     *
     * @param settleModel
     * @param view
     */
    void approveWfYieldSettle(WfYieldSettle yieldSettle, WfYieldSettleModel settleModel, Integer view)
        throws RuntimeException, LogicalException;

}
