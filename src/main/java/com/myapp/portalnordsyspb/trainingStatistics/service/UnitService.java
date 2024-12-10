package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.trainingStatistics.dto.response.UnitResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Direction;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Unit;

import java.util.List;
import java.util.Optional;

public interface UnitService {

    List<UnitResponseDto> getUnitResponseDtoByPeriodIdAndDirectionId(Long period_id, Direction direction);
    Unit getUnitById(long unitId);
}
