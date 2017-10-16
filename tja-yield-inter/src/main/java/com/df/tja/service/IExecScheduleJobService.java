/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.service
 *
 * IScheduleJobService.java
 * 
 * 2017年10月16日-下午4:27:11
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.service;

import com.df.framework.base.service.IBaseService;

/**
 * <p>IScheduleJobService</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月16日 下午4:27:11</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */

public interface IExecScheduleJobService extends IBaseService {

    /**
     * <p>描述 : 调用存储过程 执行job</p>
     *
     */
    void updateScheduledTaskFirJob() throws RuntimeException;

    /**
     * <p>描述 : </p>
     *
     */
    void updateScheduledTaskSecJob() throws RuntimeException;

}
