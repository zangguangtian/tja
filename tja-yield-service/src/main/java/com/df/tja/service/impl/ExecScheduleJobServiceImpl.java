/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.service.impl
 *
 * ScheduleJobServiceImpl.java
 * 
 * 2017年10月16日-下午4:27:54
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.tja.constant.TjaConstant;
import com.df.tja.dao.IScheduleJobDao;
import com.df.tja.service.IExecScheduleJobService;

/**
 * <p>ScheduleJobServiceImpl</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月16日 下午4:27:54</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */
@Service("execScheduleJobService")
public class ExecScheduleJobServiceImpl extends BaseServiceImpl implements IExecScheduleJobService {

    @Autowired
    private IScheduleJobDao scheduleJobDao;

    /** 
     * @see com.df.tja.service.IExecScheduleJobService#updateScheduledTaskFirJob()
     */
    @Override
    public void updateScheduledTaskFirJob() throws RuntimeException {
        try {
            scheduleJobDao.updateWriteStoreProce(TjaConstant.WriteStoreProce.USP_SYS_SCHEDULED_TASK_01);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }

    /** 
     * @see com.df.tja.service.IExecScheduleJobService#updateScheduledTaskSecJob()
     */
    @Override
    public void updateScheduledTaskSecJob() throws RuntimeException {
        try {
            scheduleJobDao.updateWriteStoreProce(TjaConstant.WriteStoreProce.USP_SYS_SCHEDULED_TASK_02);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
