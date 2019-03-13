package com.wkr.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TimerTask {
    @Scheduled(cron = "0/2 * * * * ?")//每隔2秒隔行一次
    public void test2() {
        System.out.println(new Date());
    }

}
