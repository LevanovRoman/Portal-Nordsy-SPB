package com.myapp.portalnordsyspb.repositories;

import com.myapp.portalnordsyspb.entities.Criterion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CriterionRepository extends JpaRepository<Criterion, Long> {
}
