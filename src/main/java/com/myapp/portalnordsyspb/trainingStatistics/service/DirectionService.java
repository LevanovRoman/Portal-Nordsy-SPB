package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.trainingStatistics.dto.request.DirectionRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.DirectionRequestDtoUpdate;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.DirectionOnlyResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.DirectionResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Direction;

import java.util.List;

public interface DirectionService {

    List<DirectionResponseDto> getAllDirectionResponseDto(Long period_id);
    Direction getDirectionById(long directionId);
    List<DirectionOnlyResponseDto> getAllDirections();

    void createDirection(DirectionRequestDto directionRequestDto);
    void updateDirection(DirectionRequestDto directionRequestDto, long directionId);
    void deleteDirection(long directionId);


}
