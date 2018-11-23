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
    
    /**
     * 
     * <p>描述 : 通过阶段ID，查询此阶段下的设计师工作进度  </p>
     *
     * @param phaseId
     * @return
     */
    List<CustOcDesignSchedule> selectDesignSchedulesById(String phaseId);
    
    /**
     * 
     * <p>描述 : 批量更新设计师工作进度的上周进度 </p>
     * <p>注意： 调用此方法只有存在上周数据时，才有可能存在更新记录</p>
     *
     * @param phaseId
     */
    void updateDesignPreSchedule(String phaseId);
}
