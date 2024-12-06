package com.myapp.portalnordsyspb.trainingStatistics.dto.response;

import java.util.List;

public record UnitResponseDto(
        long weekdayId,
        String weekdayName,
        long unitId,
        int value
) {
}
