package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.trainingStatistics.dto.response.DirectionResponseDto;

import java.util.List;

public interface DirectionService {

    List<DirectionResponseDto> getAllDirectionResponseDto(Long period_id);
}
