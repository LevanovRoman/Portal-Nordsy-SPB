package com.myapp.portalnordsyspb.trainingStatistics.repository;

import com.myapp.portalnordsyspb.trainingStatistics.entity.UnitDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnitDetailsRepository extends JpaRepository<UnitDetails, Long> {

    Optional<UnitDetails> findByUnitId(long unit_id);
}
