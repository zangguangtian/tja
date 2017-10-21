/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.quartz.schedule.job
 *
 * ITHrSyncJob.java
 * 
 * 2017年10月17日-上午9:09:44
 *
 * 2017 上海一勤信息技术有限公司-版权所有
 */

package com.df.tja.quartz.schedule.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.df.framework.exception.LogicalException;
import com.df.framework.util.DateUtil;
import com.df.framework.util.LoggerUtil;
import com.df.quartz.schedule.domain.QrtzScheduleJob;
import com.df.quartz.schedule.job.SQLExecJob;
import com.df.quartz.schedule.service.IScheduleJobService;
import com.df.tja.service.IDataSyncService;

/**
 * <p>ITHrSyncJob</p>
 * 
 * <p>描述：部门、员工、用户相关接口数据同步Job</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月17日 上午9:09:44</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 * 
 */

public class ITHrSyncJob extends QuartzJobBean {

    @Autowired
    private IDataSyncService dataSyncService;

    @Autowired
    private IScheduleJobService scheduleJobService;

    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String jobCode = context.getJobDetail().getKey().getName();
        LoggerUtil.info(SQLExecJob.class, DateUtil.toSeconds(new Date()) + " 开始调度  任务group：default 任务名称：" + jobCode);

        try {
            QrtzScheduleJob scheduleJob = scheduleJobService.queryByJobCode(jobCode);
            if (scheduleJob != null) {
                if (scheduleJobService.checkJobCanExec(scheduleJob)) {
                    // 同步部门
                    String value = dataSyncService.createAndQuerySyncData("getDepts", "");
                    dataSyncService.syncDepts(value);

                    //同步员工 
                    value = dataSyncService.createAndQuerySyncData("getEmployees", "");
                    dataSyncService.syncEmployees(value);

                    //同步账号，部门和员工要求优先同步
                    value = dataSyncService.createAndQuerySyncData("getAccounts", "");
                    dataSyncService.syncAccounts(value);
                } else {
                    scheduleJobService.modifyScheduleJobStatus(scheduleJob.getId());
                }
            }
        } catch (LogicalException ex) {
            ex.printStackTrace();
            LoggerUtil.error(SQLExecJob.class, "", ex);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            LoggerUtil.error(SQLExecJob.class, "", ex);
        }
    }

}
