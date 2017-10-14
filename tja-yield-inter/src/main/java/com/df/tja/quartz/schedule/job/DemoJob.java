package com.df.tja.quartz.schedule.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class DemoJob extends QuartzJobBean {

    private static Logger logger = LoggerFactory.getLogger(DemoJob.class);
    //判断作业是否执行的旗标
    private boolean isRunning = false;

    private String jobName;

    //定义任务执行体
    public void executeInternal(JobExecutionContext ctx) throws JobExecutionException {

        if (!isRunning) {
            long start = System.currentTimeMillis();

            String group = ctx.getJobDetail().getKey().getGroup();
            String name = ctx.getJobDetail().getKey().getName();
            System.out.println("开始调度  任务group：" + group + " 任务名称：" + name);

            isRunning = true;

            //调用业务逻辑方法

            //List<Dict> all = dictService.getAll(); 这是本项目中的service 
            //logger.info("--------"+all);


            //logger.info(jobName+ " - admin_name: "+ user.getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("暂时休眠出错 ！");
            }

            isRunning = false;
            System.out.println("结束调度  任务");
            System.out.println(System.currentTimeMillis() - start);
        }
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

}
