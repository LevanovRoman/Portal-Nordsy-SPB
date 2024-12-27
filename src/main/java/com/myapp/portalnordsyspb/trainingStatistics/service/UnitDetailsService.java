package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.trainingStatistics.dto.response.DiagramResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.UnitDetailsResponseDto;

import java.util.List;

public interface UnitDetailsService {

    UnitDetailsResponseDto getUnitDetailsByUnitId(long unitId);

    List<DiagramResponseDto> getDataForDiagram(int year);
}
