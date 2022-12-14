/**
 * 项目名称:df-pro-service
 *
 * com.df.project.dao
 *
 * IOcSettleYieldDao.java
 * 
 * 2017年9月18日-下午6:12:26
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.dao;

import java.util.Date;

import com.df.framework.base.dao.IBaseDao;
import com.df.tja.domain.OcSettleYield;

/**
 * <p>IOcSettleYieldDao</p>
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

public interface IOcSettleYieldDao extends IBaseDao {

    /**
     * 
     * <p>描述 : </p>
     *
     * @param id
     * @return
     */
    OcSettleYield querySettleYield(String id);

    /**
     * <p>描述 : </p>
     *
     * @param date
     */
    void insertMegerSettleYield(Date date);

}
