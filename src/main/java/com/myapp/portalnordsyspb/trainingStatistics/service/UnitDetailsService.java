package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.trainingStatistics.dto.request.UnitDetailsRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.DiagramResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.UnitDetailsResponseDto;

import java.util.List;

public interface UnitDetailsService {

    UnitDetailsResponseDto getUnitDetailsByUnitId(long unitId);

//    int getQuantityPersonsPerMonth(int monthNumber);

    List<DiagramResponseDto> getDataForDiagram(int year);

//    void createUnitDetails(UnitDetailsRequestDto unitDetailsRequestDto, long unitId);
//
//    void updateUnitDetails(UnitDetailsRequestDto unitDetailsRequestDto, long unitDetailsId);
//
//    void deleteUnitDetails(long unitDetailsId);
}
