package com.myapp.portalnordsyspb.inspectionCEO.service;

import com.myapp.portalnordsyspb.inspectionCEO.dto.request.InspectionRequestDto;
import com.myapp.portalnordsyspb.inspectionCEO.dto.response.InspectionResponseDto;
import com.myapp.portalnordsyspb.inspectionCEO.entity.Inspection;

import java.util.List;

public interface InspectionService {

    List<InspectionResponseDto> getInspectionsByWorkshopId(Long id);

    Inspection getInspectionById(long inspectionId);

    void createInspection(InspectionRequestDto inspectionRequestDto);

    void deleteInspection(long inspectionId);
}
