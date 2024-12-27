package com.myapp.portalnordsyspb.trainingStatistics.dto.request;

public record PeriodRequestDto(
        String interval,
        String month,
        int number
) {
}
