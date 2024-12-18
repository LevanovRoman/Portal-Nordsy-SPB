package com.myapp.portalnordsyspb.visitCounter.service;

import com.myapp.portalnordsyspb.visitCounter.entity.VisitHistory;
import com.myapp.portalnordsyspb.visitCounter.repository.VisitHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class VisitCounterService {

    private final AtomicInteger visitCount = new AtomicInteger(0);
    private final VisitHistoryRepository visitHistoryRepository;
//    private int previousDayCount = 0;

    // Метод для увеличения счетчика
    public void incrementVisitCount() {
        visitCount.incrementAndGet();
    }

    // Получение текущего значения
    public int getCurrentVisitCount() {
        return visitCount.get();
    }

    // Ежедневный сброс счетчика в полночь
//    @Scheduled(cron = "0 0 0 * * *") // каждый день в полночь
//    public void resetDailyCounter() {
//        previousDayCount = visitCount.get();
//        visitCount.set(0);
//    }

    // Сохранение данных о посещениях за день в базу данных
    @Scheduled(cron = "0 0 0 * * *") // Каждый день в полночь
    public void saveVisitHistory() {
        int count = visitCount.getAndSet(0); // Сбрасываем текущий счетчик
        VisitHistory visitHistory = new VisitHistory(LocalDate.now().minusDays(1), count);
        visitHistoryRepository.save(visitHistory);
    }
}

