package com.myapp.portalnordsyspb.visitCounter.service;

import com.myapp.portalnordsyspb.visitCounter.entity.VisitHistory;
import com.myapp.portalnordsyspb.visitCounter.repository.VisitHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class VisitCounterService {

    private final AtomicInteger visitCount = new AtomicInteger(0);
    private final VisitHistoryRepository visitHistoryRepository;

    // Метод для увеличения счетчика
    public void incrementVisitCount() {
        visitCount.incrementAndGet();
    }

    // Получение текущего значения
    public int getCurrentVisitCount() {
        return visitCount.get();
    }

    // Сохранение данных о посещениях за день в базу данных
    @Scheduled(cron = "0 0 0 * * *") // Каждый день в полночь
    public void saveVisitHistory() {
        int count = visitCount.getAndSet(0); // Сбрасываем текущий счетчик
        VisitHistory visitHistory = new VisitHistory(LocalDate.now().minusDays(1), count);
        visitHistoryRepository.save(visitHistory);
    }

    // Получение количества посещений за последнюю неделю
    public Integer getVisitsForLastWeek() {
        LocalDate today = LocalDate.now();
        LocalDate oneWeekAgo = today.minusDays(7);
        return visitHistoryRepository.countVisitsBetweenDates(oneWeekAgo, today);
    }

    // Получение количества посещений за последний месяц
    public Integer getVisitsForLastMonth() {
        LocalDate today = LocalDate.now();
        LocalDate oneMonthAgo = today.minusMonths(1);
        return visitHistoryRepository.countVisitsBetweenDates(oneMonthAgo, today);
    }

    public List<VisitHistory> getDailyVisitsForLastWeek() {
        LocalDate today = LocalDate.now();
        LocalDate oneWeekAgo = today.minusDays(7);
        return visitHistoryRepository.findDailyVisitsBetweenDates(oneWeekAgo, today);
    }

    public List<VisitHistory> getDailyVisitsForLastMonth() {
        LocalDate today = LocalDate.now();
        LocalDate oneMonthAgo = today.minusMonths(1);
        return visitHistoryRepository.findDailyVisitsBetweenDates(oneMonthAgo, today);
    }

}

