package com.example.scheduled.task;

import com.example.scheduled.job.Action;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class QuartzTask{
    private SchedulerFactory  schedulerFactory;

    public QuartzTask() throws SchedulerException {
        schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        JobDetail job = JobBuilder.newJob(Action.class)
                .withIdentity("job1","group1")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1","group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
        scheduler.scheduleJob(job,trigger);
        //scheduler.start();
    }

    private void updateJob(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * 10);
                    Scheduler scheduler = schedulerFactory.getScheduler();
                    Trigger trigger = TriggerBuilder.newTrigger()
                            .withIdentity("trigger1","group1")
                            .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
                            .build();
                    scheduler.rescheduleJob(TriggerKey.triggerKey("trigger1","group1"),trigger);
                    Thread.sleep(1000 * 10);
                    deleteJob();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (SchedulerException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void deleteJob() throws SchedulerException {
        Scheduler scheduler = schedulerFactory.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey("trigger1","group1");
        scheduler.deleteJob(scheduler.getTrigger(triggerKey).getJobKey());
    }


    /*public void modifyJobTime( String triggerName, String triggerGroupName, String cron) {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }

            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                *//** 方式一 ：调用 rescheduleJob 开始 *//*
                // 触发器
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                // 触发器名,触发器组
                triggerBuilder.withIdentity(triggerName, triggerGroupName);
                triggerBuilder.startNow();
                // 触发器时间设定
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
                // 创建Trigger对象
                trigger = (CronTrigger) triggerBuilder.build();
                // 方式一 ：修改一个任务的触发时间
                sched.rescheduleJob(triggerKey, trigger);
                *//** 方式一 ：调用 rescheduleJob 结束 *//*

                *//** 方式二：先删除，然后在创建一个新的Job  *//*
                //JobDetail jobDetail = sched.getJobDetail(JobKey.jobKey(jobName, jobGroupName));
                //Class<? extends Job> jobClass = jobDetail.getJobClass();
                //removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
                //addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron);
                *//** 方式二 ：先删除，然后在创建一个新的Job *//*
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

}
