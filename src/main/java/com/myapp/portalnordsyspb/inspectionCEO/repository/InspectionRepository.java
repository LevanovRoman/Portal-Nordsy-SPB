package com.myapp.portalnordsyspb.inspectionCEO.repository;

import com.myapp.portalnordsyspb.inspectionCEO.dto.response.InspectionResponseDto;
import com.myapp.portalnordsyspb.inspectionCEO.entity.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InspectionRepository extends JpaRepository<Inspection, Long> {

//    List<Inspection> findTop2ByOrderByWorkshopIdDesc(Long workshop_id);
    List<Inspection> findTop2ByWorkshopIdOrderByWorkshopId(Long workshopId);
}
