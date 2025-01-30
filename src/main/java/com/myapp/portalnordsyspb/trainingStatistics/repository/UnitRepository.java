package com.myapp.portalnordsyspb.trainingStatistics.repository;

import com.myapp.portalnordsyspb.trainingStatistics.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UnitRepository extends JpaRepository<Unit, Long> {

    Optional<Unit> findByDirectionIdAndPeriodIdAndWeekdayId(Long direction_id, Long period_id, Long weekday_id);

    @Query(value = """
        SELECT u.*
        FROM unit u
        JOIN unit_details ud ON u.id = ud.unit_id
        JOIN details_persons dp ON ud.id = dp.unit_details_id
        WHERE u.direction_id = :direction_id
          AND u.period_id = :period_id
          AND u.weekday_id = :weekday_id
          AND dp.persons LIKE %:tub_number%
    """, nativeQuery = true)
    Optional<Unit> findFilterByTubNumber(@Param("direction_id") long directionId,
                                         @Param("period_id") long periodId,
                                         @Param("weekday_id") long weekdayId,
                                         @Param("tub_number") String tubNumber);

    @Query(value = """
        SELECT u.* FROM unit u
        JOIN unit_values uv ON u.id = uv.unit_id
        WHERE u.direction_id = :direction_id
           AND u.period_id = :period_id
           AND u.weekday_id = :weekday_id
           AND uv.values = :unit_value
    """, nativeQuery = true)
    Optional<Unit> findFilterByUnitValue(@Param("direction_id") long directionId,
                                         @Param("period_id") long periodId,
                                         @Param("weekday_id") long weekdayId,
                                         @Param("unit_value") Integer unitValue);

    @Query(value = """
        SELECT u.* FROM unit u
        JOIN unit_details ud ON u.id = ud.unit_id
        JOIN details_persons dp ON ud.id = dp.unit_details_id
        JOIN unit_values uv ON u.id = uv.unit_id
        WHERE u.direction_id = :direction_id
           AND u.period_id = :period_id
           AND u.weekday_id = :weekday_id
           AND uv.values = :unit_value
           AND dp.persons LIKE %:tub_number%
    """, nativeQuery = true)
    Optional<Unit> findFilterByTabNumberAndUnitValue(@Param("direction_id") long directionId,
                                         @Param("period_id") long periodId,
                                         @Param("weekday_id") long weekdayId,
                                         @Param("unit_value") Integer unitValue,
                                         @Param("tub_number") String tubNumber);

}
