package com.myapp.portalnordsyspb.statisticPPU;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TestTask {
//    @Scheduled(fixedRate = 5000) // каждые 5 секунд
    public void reportCurrentTime() {
        System.out.println("Текущее время: " + System.currentTimeMillis());
    }

//    @Scheduled(cron = "0 0/1 * * * ?") // каждую минуту
//    public void performTask() {
//        System.out.println("Выполнение задачи: " + System.currentTimeMillis());
//    }

    @Scheduled(cron = "0 05 11 * * *")
    public void performTask() {
        System.out.println("Выполнение задачи: " + System.currentTimeMillis());
    }

    @Scheduled(cron = "0 10 11 * * *")
    public void performTask2() {
        System.out.println("Выполнение задачи: 11-10");
    }
}
