package com.myapp.portalnordsyspb.trainingStatistics.dto.request;

import java.time.LocalDate;
import java.util.List;

public record UnitRequestDto(
        List<Integer> values,
        boolean completed,
        LocalDate date,
        List<String> tabNumberList
) {
}
