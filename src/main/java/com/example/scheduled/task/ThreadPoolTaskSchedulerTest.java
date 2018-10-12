package com.example.scheduled.task;

import com.example.scheduled.job.Action2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Component
public class ThreadPoolTaskSchedulerTest{

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private static String cronOld = "0/5 * * * * ?";//5秒执行一次

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.initialize();
        return threadPoolTaskScheduler;
    }

    //@PostConstruct
    public void start() {
        CronTrigger cronTrigger = new CronTrigger(cronOld);
        threadPoolTaskScheduler.schedule(new Action2(),cronTrigger);
    }
}
