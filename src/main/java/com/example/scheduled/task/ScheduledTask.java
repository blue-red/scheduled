package com.example.scheduled.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTask {

    //@Scheduled(fixedRate = 5000)
    public void doSomething(){
        System.out.println("哈哈哈哈哈"+new Date());
    }

   /* @Scheduled(initialDelay = 10000,fixedRate = 5000)
    public void doSomething(){
        System.out.println("哈哈哈哈哈"+new Date());
    }*/

   /* @Scheduled(fixedDelay = 5000)
    public void doSomething(){
        System.out.println("哈哈哈哈哈"+new Date());
    }*/

    //@Scheduled(cron = "0/5 * * * * ?")
    /*public void doSomething(){
        System.out.println("哈哈哈哈哈"+new Date());
    }*/


}
