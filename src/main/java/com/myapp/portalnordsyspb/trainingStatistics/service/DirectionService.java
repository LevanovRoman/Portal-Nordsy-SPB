package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.trainingStatistics.dto.request.DirectionRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.FilterDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.DirectionOnlyResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.DirectionResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.DirectionUpdateResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Direction;

import java.util.List;

public interface DirectionService {

    List<DirectionResponseDto> getAllDirectionResponseDto(Long period_id, FilterDto filterDto);
    Direction getDirectionById(long directionId);
    List<DirectionOnlyResponseDto> getAllDirections();
//    DirectionUpdateResponseDto getDirection(long directionId);

    void createDirection(DirectionRequestDto directionRequestDto);
    void updateDirection(DirectionRequestDto directionRequestDto, long directionId);
    void deleteDirection(long directionId);

//    List<DirectionResponseDto> getAllDirectionResponseDtoFilter(Long periodId, PeriodRequestFilterDto periodRequestFilterDto);
}
