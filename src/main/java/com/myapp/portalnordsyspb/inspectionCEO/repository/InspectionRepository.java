package com.myapp.portalnordsyspb.inspectionCEO.repository;

import com.myapp.portalnordsyspb.inspectionCEO.entity.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspectionRepository extends JpaRepository<Inspection, Long> {
}
