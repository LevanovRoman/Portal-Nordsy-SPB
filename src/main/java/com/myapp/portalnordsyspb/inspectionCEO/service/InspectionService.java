package com.myapp.portalnordsyspb.inspectionCEO.service;

import com.myapp.portalnordsyspb.inspectionCEO.dto.request.InspectionRequestDto;
import com.myapp.portalnordsyspb.inspectionCEO.dto.response.InspectionResponseDto;
import com.myapp.portalnordsyspb.inspectionCEO.entity.Inspection;

import java.util.List;

public interface InspectionService {

    List<InspectionResponseDto> getAllInspections();

    Inspection getInspectionById(long inspectionId);

    void createInspection(InspectionRequestDto inspectionRequestDto);

    void updateInspection(InspectionRequestDto inspectionRequestDto, long inspectionId);

    void deleteInspection(long inspectionId);
}
