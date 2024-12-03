package com.myapp.portalnordsyspb.trainingStatistics.repository;

import com.myapp.portalnordsyspb.trainingStatistics.entity.Weekday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeekdayRepository extends JpaRepository<Weekday, Long> {
}
