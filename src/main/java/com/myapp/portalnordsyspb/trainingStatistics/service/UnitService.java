package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.trainingStatistics.dto.response.UnitResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Direction;

import java.util.List;

public interface UnitService {

    List<UnitResponseDto> getUnitResponseDtoByPeriodIdAndDirectionId(Long period_id, Direction direction);
}
