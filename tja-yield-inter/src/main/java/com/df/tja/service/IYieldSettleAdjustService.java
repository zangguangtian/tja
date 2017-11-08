/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.service
 *
 * IYieldSettleAdjustService.java
 * 
 * 2017年11月6日-下午3:23:25
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.service;

import java.util.Map;

import com.df.framework.base.service.IBaseService;
import com.df.framework.exception.LogicalException;
import com.df.tja.domain.WfYieldSettle;
import com.df.tja.domain.cust.WfYieldSettleModel;

/**
 * <p>IYieldSettleAdjustService</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年11月6日 下午3:23:25</p>
 *
 * @author wangchangjiu
 * 
 * @version 1.0.0
 * 
 */

public interface IYieldSettleAdjustService extends IBaseService {

    /**
     * <p>描述 : </p>
     *
     * @param outParams
     * @param yieldSettle
     */
    void queryYieldSettle(Map<String, Object> outParams, WfYieldSettle yieldSettle) throws RuntimeException;

    /**
     * <p>描述 : </p>
     *
     * @param settleModel
     */
    void createOrEditYieldSettle(WfYieldSettleModel settleModel) throws RuntimeException, LogicalException;

}
