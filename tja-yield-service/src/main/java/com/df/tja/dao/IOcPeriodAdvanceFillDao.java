/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.dao
 *
 * IOcPeriodAdvanceFillDao.java
 * 
 * 2017年9月25日-下午4:11:00
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.dao;

import java.util.List;

import com.df.framework.base.dao.IBaseDao;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.tja.domain.OcPeriodAdvanceFill;

/**
 * <p>IOcPeriodAdvanceFillDao</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月25日 下午4:11:00</p>
 *
 * @author tc
 * 
 * @version 1.0.0
 * 
 */

public interface IOcPeriodAdvanceFillDao extends IBaseDao {

    /**
     * 
     * <p>描述 : </p>
     *
     * @return
     */
    List<OcPeriodAdvanceFill> queryAdvanceFillList(OcPeriodAdvanceFill advanceFill, Pagination pagination);
}
