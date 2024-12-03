package com.myapp.portalnordsyspb.trainingStatistics.dto.response;

import java.util.List;

public record UnitResponseDto(
        List<Integer> unitsForWeek
) {
}
