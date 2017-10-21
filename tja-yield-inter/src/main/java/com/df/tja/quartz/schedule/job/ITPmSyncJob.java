/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.quartz.schedule.job
 *
 * ITPmSyncJob.java
 * 
 * 2017年10月17日-上午9:10:17
 *
 * 2017 上海一勤信息技术有限公司-版权所有
 */

package com.df.tja.quartz.schedule.job;

import java.util.Date;
import java.util.List;

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
import com.df.tja.domain.ItProContractInfo;
import com.df.tja.domain.ItProjectInfo;
import com.df.tja.service.IDataSyncService;

/**
 * <p>ITPmSyncJob</p>
 * 
 * <p>描述：项目、合同、项目角色、专业等相关接口数据同步Job</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月17日 上午9:10:17</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 * 
 */

public class ITPmSyncJob extends QuartzJobBean {

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
                    // 同步项目
                    String value = dataSyncService.createAndQuerySyncData("getItems", "");
                    List<ItProjectInfo> projects = dataSyncService.syncItems(value);

                    if (projects != null && !projects.isEmpty()) {
                        //同步前先将接口表中的数据清空
                        ItProContractInfo delObj = new ItProContractInfo();
                        dataSyncService.deleteByObject(ItProContractInfo.class, delObj);

                        ItProjectInfo projectInfo = null;
                        for (int i = 0; i < projects.size(); i++) {
                            projectInfo = projects.get(i);
                            //合同
                            value = dataSyncService.createAndQuerySyncData("getContractOfItem", projectInfo.getId());
                            dataSyncService.syncContractOfItem(value);

                            //分包合同
                            value = dataSyncService.createAndQuerySyncData("getEpibolyContractOfItem",
                                projectInfo.getId());
                            dataSyncService.syncEpibolyContractOfItem(value, i == projects.size() - 1);
                        }
                    }
                } else {
                    scheduleJobService.modifyScheduleJobStatus(scheduleJob.getId());
                }
            }
        } catch (LogicalException ex) {
            LoggerUtil.error(SQLExecJob.class, "", ex);
        } catch (RuntimeException ex) {
            LoggerUtil.error(SQLExecJob.class, "", ex);
        }
    }

}
