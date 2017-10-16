/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.dao
 *
 * IScheduleJobDao.java
 * 
 * 2017年10月16日-下午4:29:23
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.dao;

import com.df.framework.base.dao.IBaseDao;

/**
 * <p>IScheduleJobDao</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月16日 下午4:29:23</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */

public interface IScheduleJobDao extends IBaseDao {

    /**
     * <p>描述 : </p>
     *
     * @param uspSysScheduledTask01
     */
    void updateWriteStoreProce(String uspSysScheduledTask01);

}
