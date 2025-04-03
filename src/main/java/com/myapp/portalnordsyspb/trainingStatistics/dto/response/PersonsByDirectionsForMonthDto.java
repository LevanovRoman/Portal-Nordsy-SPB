package com.myapp.portalnordsyspb.trainingStatistics.dto.response;

import java.util.List;

public record PersonsByDirectionsForMonthDto(
    long id,
    String month,
    List<PersonsByDirectionDto> personsByDirectionDtoList
) {
}
