package com.example.scheduled.job;

import java.util.Date;

public class Action2 implements Runnable{
    public Action2() {

    }
    @Override
    public void run() {
        System.out.println("我是Action2 " + new Date());
    }
}
