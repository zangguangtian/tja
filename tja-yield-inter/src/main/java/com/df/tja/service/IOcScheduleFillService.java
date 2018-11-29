package com.df.tja.service;

import com.df.framework.base.service.IBaseService;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.tja.domain.cust.OcCurrweekSchedule;

import java.util.List;

/**
 * <p>IOcScheduleFillService </p>
 *
 * <p>描述： </p>
 *
 * <p>备注：</p>
 *
 * <p>2018-11-27 17:33</p>
 *
 * @author deng.jiayan
 * @version 1.0.0
 */
public interface IOcScheduleFillService extends IBaseService{

    /**
     * <p>描述 : 主项进展页面简化模式</p>
     *
     * @param proId
     * @return
     */
    List<OcCurrweekSchedule> querySimleByProId(String proId,Pagination pagination,Integer state);
    /**
     * <p>描述 : 主项进展页面完整模式</p>
     *
     * @param proId
     * @return
     */
    List<OcCurrweekSchedule> queryFullByProId(String proId,Pagination pagination,Integer state);

}
