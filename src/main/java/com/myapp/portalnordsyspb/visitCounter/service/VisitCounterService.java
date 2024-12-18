package com.myapp.portalnordsyspb.visitCounter.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class VisitCounterService {

    private final AtomicInteger visitCount = new AtomicInteger(0);
    private int previousDayCount = 0;

    // Метод для увеличения счетчика
    public void incrementVisitCount() {
        visitCount.incrementAndGet();
    }

    // Получение текущего значения
    public int getCurrentVisitCount() {
        return visitCount.get();
    }

    // Получение значения за предыдущий день
    public int getPreviousDayCount() {
        return previousDayCount;
    }

    // Ежедневный сброс счетчика в полночь
    @Scheduled(cron = "0 0 0 * * *") // каждый день в полночь
    public void resetDailyCounter() {
        previousDayCount = visitCount.get();
        visitCount.set(0);
    }
}

