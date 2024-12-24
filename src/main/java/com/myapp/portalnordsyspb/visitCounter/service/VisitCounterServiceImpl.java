package com.myapp.portalnordsyspb.visitCounter.service;

import com.myapp.portalnordsyspb.visitCounter.entity.VisitHistory;
import com.myapp.portalnordsyspb.visitCounter.repository.VisitHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class VisitCounterServiceImpl implements VisitCounterService{

    private final AtomicInteger visitCount = new AtomicInteger(0);
    private final VisitHistoryRepository visitHistoryRepository;

    // Метод для увеличения счетчика
    @Override
    public void incrementVisitCount() {
        visitCount.incrementAndGet();
    }

    // Получение текущего значения
    @Override
    public int getCurrentVisitCount() {
        return visitCount.get();
    }

    // Сохранение данных о посещениях за день в базу данных
    @Override
    @Transactional
//    @Scheduled(cron = "0 0 0 * * *") // Каждый день в полночь
    @Scheduled(cron = "0 */15 * * * *") // 5 min
    public void saveVisitHistory() {
        int count = visitCount.getAndSet(0); // Сбрасываем текущий счетчик
        VisitHistory visitHistory = new VisitHistory(LocalDate.now().minusDays(1), count);
        visitHistoryRepository.save(visitHistory);
    }

    // Получение количества посещений за последнюю неделю
    @Override
    public Integer getVisitsForLastWeek() {
        LocalDate today = LocalDate.now();
        LocalDate oneWeekAgo = today.minusDays(7);
        return visitHistoryRepository.countVisitsBetweenDates(oneWeekAgo, today);
    }

    // Получение количества посещений за последний месяц
    @Override
    public Integer getVisitsForLastMonth() {
        LocalDate today = LocalDate.now();
        LocalDate oneMonthAgo = today.minusMonths(1);
        return visitHistoryRepository.countVisitsBetweenDates(oneMonthAgo, today);
    }

    @Override
    public List<VisitHistory> getDailyVisitsForLastWeek() {
        LocalDate today = LocalDate.now();
        LocalDate oneWeekAgo = today.minusDays(7);
        return visitHistoryRepository.findDailyVisitsBetweenDates(oneWeekAgo, today);
    }

    @Override
    public List<VisitHistory> getDailyVisitsForLastMonth() {
        LocalDate today = LocalDate.now();
        LocalDate oneMonthAgo = today.minusMonths(1);
        return visitHistoryRepository.findDailyVisitsBetweenDates(oneMonthAgo, today);
    }

    //    // Получение значения за предыдущий день
//    public int getPreviousDayCount() {
//        return previousDayCount;
//    }
//
//    // Ежедневный сброс счетчика в полночь
//    @Scheduled(cron = "0 0 0 * * *") // каждый день в полночь
//    public void resetDailyCounter() {
//        previousDayCount = visitCount.get();
//        visitCount.set(0);
//    }

}

