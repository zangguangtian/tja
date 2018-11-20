/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.dao
 *
 * IOcDesignScheduleDao.java
 * 
 * 2018年11月20日-下午2:48:04
 *
 * 2018 上海一勤信息技术有限公司-版权所有 
 */

package com.df.tja.dao;

import java.util.List;

import com.df.framework.base.dao.IBaseDao;
import com.df.tja.domain.cust.CustOcDesignSchedule;

/**
 * <p>IOcDesignScheduleDao</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2018年11月20日 下午2:48:04</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 * 
 */

public interface IOcDesignScheduleDao extends IBaseDao {
    
    List<CustOcDesignSchedule> selectDesignSchedulesById(String phaseId);
}
