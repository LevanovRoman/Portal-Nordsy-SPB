package com.myapp.portalnordsyspb.trainingStatistics.dto.request;

import java.util.List;

public record UnitRequestDto(
        List<Integer> values,
        boolean completed,
        String date,
        List<String> tabNumberList
) {
}
