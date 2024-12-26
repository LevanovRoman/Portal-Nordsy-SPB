package com.myapp.portalnordsyspb.visitCounter.repository;

import com.myapp.portalnordsyspb.visitCounter.entity.VisitHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
public interface VisitHistoryRepository extends JpaRepository<VisitHistory, Long> {

    VisitHistory findByDate(LocalDate date);

    // Подсчет посещений за указанный период
    @Query("SELECT SUM(v.visitCount) FROM VisitHistory v WHERE v.date BETWEEN :startDate AND :endDate")
    Integer countVisitsBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // Получение записей за указанный период
    @Query("SELECT v FROM VisitHistory v WHERE v.date BETWEEN :startDate AND :endDate")
    List<VisitHistory> findVisitsBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // данные для каждого дня в неделе или месяце
    @Query("SELECT v FROM VisitHistory v WHERE v.date BETWEEN :startDate AND :endDate ORDER BY v.date")
    List<VisitHistory> findDailyVisitsBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
