package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.trainingStatistics.dto.request.UnitDetailsRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.UnitDetailsResponseDto;

public interface UnitDetailsService {

    UnitDetailsResponseDto getUnitDetailsByUnitId(long unitId);

    void createUnitDetails(UnitDetailsRequestDto unitDetailsRequestDto, long unitId);

    void updateUnitDetails(UnitDetailsRequestDto unitDetailsRequestDto, long unitDetailsId);

    void deleteUnitDetails(long unitDetailsId);
}
