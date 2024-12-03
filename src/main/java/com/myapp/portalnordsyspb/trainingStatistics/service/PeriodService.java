package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.trainingStatistics.dto.response.PeriodResponseDto;

import java.util.List;

public interface PeriodService {

    List<PeriodResponseDto> getAllPeriodResponseDto();
}
