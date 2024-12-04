package com.myapp.portalnordsyspb.trainingStatistics.dto.request;

public record UnitRequestDto(
        long directionId,
        long weekdayId,
        int value
) {
}
