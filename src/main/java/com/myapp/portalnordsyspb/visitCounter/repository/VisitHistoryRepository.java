package com.myapp.portalnordsyspb.visitCounter.repository;

import com.myapp.portalnordsyspb.visitCounter.entity.VisitHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface VisitHistoryRepository extends JpaRepository<VisitHistory, Long> {
    VisitHistory findByDate(LocalDate date);
}
