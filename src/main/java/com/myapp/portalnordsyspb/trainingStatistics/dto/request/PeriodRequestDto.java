package com.myapp.portalnordsyspb.trainingStatistics.dto.request;

import java.util.List;

public record PeriodRequestDto(
        String interval,
        String month,
        int number,
        List<UnitRequestDto> unitRequestDtoList
) {
}
