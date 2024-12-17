package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.trainingStatistics.dto.request.FilterDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.PeriodRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.PeriodResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Period;

import java.util.List;

public interface PeriodService {

//    List<PeriodResponseDto> getAllPeriodResponseDto();
    List<PeriodResponseDto> getAllPeriodResponseDto(FilterDto filterDto);

    Period getPeriodById(long periodId);

    void createPeriod(PeriodRequestDto periodRequestDto);

    void updatePeriod(PeriodRequestDto periodRequestDto, long periodId);

    void deletePeriod(long periodId);

//    List<PeriodResponseDto> getAllPeriodWithFilter(PeriodRequestFilterDto periodRequestFilterDto);
}
