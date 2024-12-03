package com.myapp.portalnordsyspb.trainingStatistics.repository;

import com.myapp.portalnordsyspb.trainingStatistics.entity.Period;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodRepository extends JpaRepository<Period, Long> {
}
