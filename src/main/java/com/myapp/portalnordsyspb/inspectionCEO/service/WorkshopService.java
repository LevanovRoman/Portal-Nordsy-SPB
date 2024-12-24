package com.myapp.portalnordsyspb.inspectionCEO.service;

import com.myapp.portalnordsyspb.inspectionCEO.dto.response.WorkshopResponseDto;

import java.util.List;

public interface WorkshopService {

    List<WorkshopResponseDto> getAllWorkshops();

}
