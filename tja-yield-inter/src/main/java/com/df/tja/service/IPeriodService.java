/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.service
 *
 * PeriodService.java
 * 
 * 2017年9月18日-下午3:43:18
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.service;

import java.util.List;

import com.df.framework.base.service.IBaseService;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.tja.domain.OcPeriodAdvanceFill;
import com.df.tja.domain.OcPeriodManage;

/**
 * <p>IPeriodService</p>
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

public interface IPeriodService extends IBaseService {

    /**
     * <p>描述 :查询 期间详情 </p>
     * 
     * @param id 期间ID
     * @return 期间实体
     * */
    OcPeriodManage queryPeriod(String id) throws RuntimeException;

    /**
     * <p>描述 :保存/修改 期间 </p>
     * 
     * @param ocPeriodManage
     * @return 期间ID
     * */
    String createOrModifyPeriod(OcPeriodManage ocPeriodManage) throws RuntimeException;

    /**
     * <p>描述 :查询 期间提前上报 </p>
     * 
     * @param advanceFill
     * @param pagination
     * @return 期间提前上报list
     * */
    List<OcPeriodAdvanceFill> queryAdvanceFillList(OcPeriodAdvanceFill advanceFill, Pagination pagination)
        throws RuntimeException;

}
