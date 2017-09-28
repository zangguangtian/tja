/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.service
 *
 * SettleYieldService.java
 * 
 * 2017年9月18日-下午3:43:18
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.service;

import com.df.framework.base.service.IBaseService;
import com.df.tja.domain.OcSettleYield;
import com.df.tja.domain.cust.OcSettleYieldMore;

/**
 * <p>ISettleYieldService</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月18日 下午3:43:18</p>
 *
 * @author tc
 * 
 * @version 1.0.0
 * 
 */

public interface ISettleYieldService extends IBaseService {

    /**
     * <p>描述 :查询 可结算产值详情 </p>
     * 
     * @param id
     * @return 
     * */
    OcSettleYieldMore querySettleYield(String id) throws RuntimeException;

    /**
     * <p>描述 :保存/修改 可结算产值 </p>
     * 
     * @param ocSettleYield
     * @return id
     * */
    String createOrModifySettleYield(OcSettleYield ocSettleYield) throws RuntimeException;

}
