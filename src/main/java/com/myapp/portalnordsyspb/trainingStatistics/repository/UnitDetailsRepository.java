package com.myapp.portalnordsyspb.trainingStatistics.repository;

import com.myapp.portalnordsyspb.trainingStatistics.entity.UnitDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UnitDetailsRepository extends JpaRepository<UnitDetails, Long> {

    Optional<UnitDetails> findByUnitId(long unit_id);

    @Query(value = "SELECT COUNT(*) FROM details_persons dp JOIN unit_details ud ON dp.unit_details_id = ud.id" +
            " WHERE EXTRACT(MONTH FROM ud.date) =:month AND EXTRACT(YEAR FROM ud.date) =:year", nativeQuery = true)
    int countPersonsForMonth(@Param("month") int month,
                             @Param("year") int year);

    @Query(value = "SELECT COUNT(*) FROM details_persons dp JOIN unit_details ud ON dp.unit_details_id = ud.id" +
            "                JOIN unit u on u.id = ud.unit_id" +
            "                JOIN public.direction d on d.id = u.direction_id" +
            "            WHERE d.id =:direction AND EXTRACT(MONTH FROM ud.date) =:month" +
            " AND EXTRACT(YEAR FROM ud.date) =:year", nativeQuery = true)
    int countPersonsForMonthForDirection(@Param("month") int month, @Param("direction") long direction,
                                         @Param("year") int year);
}
