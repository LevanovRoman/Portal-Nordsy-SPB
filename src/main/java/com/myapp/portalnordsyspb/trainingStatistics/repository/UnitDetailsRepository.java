package com.myapp.portalnordsyspb.trainingStatistics.repository;

import com.myapp.portalnordsyspb.trainingStatistics.entity.UnitDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UnitDetailsRepository extends JpaRepository<UnitDetails, Long> {

    Optional<UnitDetails> findByUnitId(long unit_id);

    @Query(value = "SELECT COUNT(*) FROM details_persons dp JOIN unit_details ud ON dp.unit_details_id = ud.id" +
            " WHERE EXTRACT(MONTH FROM ud.date) =:month;\n", nativeQuery = true)
    int countPersonsForMonth(@Param("month") int month);
}
