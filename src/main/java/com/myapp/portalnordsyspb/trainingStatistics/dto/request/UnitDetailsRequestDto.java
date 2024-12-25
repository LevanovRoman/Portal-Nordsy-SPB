package com.myapp.portalnordsyspb.trainingStatistics.dto.request;

import java.time.LocalDate;
import java.util.List;

public record UnitDetailsRequestDto(
        LocalDate date,
        List<String> tabNumberList
) {
}
