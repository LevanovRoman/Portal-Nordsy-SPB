package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.trainingStatistics.dto.response.UnitResponseDto;

public interface UnitService {

    UnitResponseDto getUnitResponseDtoByPeriodIdAndDirectionId(Long period_id, Long direction_id);
}
