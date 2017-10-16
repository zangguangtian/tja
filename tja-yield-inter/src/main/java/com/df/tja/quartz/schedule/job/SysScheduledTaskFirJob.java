/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.quartz.schedule.job
 *
 * SysScheduledTask01.java
 * 
 * 2017年10月16日-下午4:33:49
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.quartz.schedule.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.df.quartz.schedule.service.IScheduleJobService;
import com.df.tja.service.IExecScheduleJobService;

/**
 * <p>SysScheduledTask01</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月16日 下午4:33:49</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */
@Component
public class SysScheduledTaskFirJob extends QuartzJobBean {

    @Autowired
    private IExecScheduleJobService execScheduleJobService;

    private IScheduleJobService scheduleJobService;

    private static Logger logger = LoggerFactory.getLogger(DemoJob.class);

    private String jobName;

    //定义任务执行体
    public void executeInternal(JobExecutionContext ctx) throws JobExecutionException {

        try {
            String group = ctx.getJobDetail().getKey().getGroup();
            String name = ctx.getJobDetail().getKey().getName();
            logger.info("开始调度  任务group：" + group + " 任务名称：" + name);

            if (scheduleJobService.existExecConditionByJobCode(ctx.getJobDetail().getKey().getName())) {
                //执行
                execScheduleJobService.updateScheduledTaskFirJob();
                logger.info("执行 job 时间：" + System.currentTimeMillis());
            }
        } catch (RuntimeException e) {
            logger.error("job SysScheduledTaskFirJob error!", e);
        }

    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

}
