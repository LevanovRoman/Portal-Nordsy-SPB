package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.trainingStatistics.dto.request.UnitRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.UnitResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Direction;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Unit;

import java.util.List;

public interface UnitService {

    List<UnitResponseDto> getUnitResponseDtoByPeriodIdAndDirectionId(Long period_id, Direction direction);
    Unit getUnitById(long unitId);

//    void createUnit(UnitRequestDto unitRequestDto, long unitId);
    void updateUnit(UnitRequestDto unitRequestDto, long unitId);
    void deleteUnit(long unitId);
}
