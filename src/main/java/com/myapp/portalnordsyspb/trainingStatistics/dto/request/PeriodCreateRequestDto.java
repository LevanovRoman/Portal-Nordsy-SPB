package com.myapp.portalnordsyspb.trainingStatistics.dto.request;

public record PeriodCreateRequestDto(
        String interval,
        String month,
        int number
) {
}
