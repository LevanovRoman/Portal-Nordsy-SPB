package com.myapp.portalnordsyspb.inspectionCEO.service;

import com.myapp.portalnordsyspb.inspectionCEO.dto.response.InspectionResponseDto;

import java.util.List;

public interface InspectionService {

    List<InspectionResponseDto> getAllInspections();


}
