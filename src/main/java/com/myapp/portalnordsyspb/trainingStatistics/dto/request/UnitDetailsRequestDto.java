package com.myapp.portalnordsyspb.trainingStatistics.dto.request;

import java.util.List;

public record UnitDetailsRequestDto(
        String date,
        List<String> tabNumberList
) {
}
