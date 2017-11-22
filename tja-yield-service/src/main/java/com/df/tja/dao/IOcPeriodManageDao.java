/**
 * 项目名称:df-pro-service
 *
 * com.df.project.dao
 *
 * IOcPeriodManageDao.java
 * 
 * 2017年9月18日-下午6:12:26
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.dao;

import java.util.List;

import com.df.framework.base.dao.IBaseDao;
import com.df.tja.domain.OcPeriodManage;

/**
 * <p>IOcPeriodManageDao</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月18日 下午6:12:26</p>
 *
 * @author tc
 * 
 * @version 1.0.0
 * 
 */

public interface IOcPeriodManageDao extends IBaseDao {

    /**
     * 
     * <p>描述 : </p>
     *
     * @param id
     * @return
     */
    OcPeriodManage queryPeriod(String id);

    /**
     * <p>描述 : </p>
     *
     * @return
     */
    List<OcPeriodManage> selectSettlePeriodForRp();

}
