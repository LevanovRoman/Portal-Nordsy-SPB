package com.myapp.portalnordsyspb.trainingStatistics.repository;

import com.myapp.portalnordsyspb.trainingStatistics.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnitRepository extends JpaRepository<Unit, Long> {

    Optional<Unit> findByDirectionIdAndPeriodIdAndWeekdayId(Long direction_id, Long period_id, Long weekday_id);
}
