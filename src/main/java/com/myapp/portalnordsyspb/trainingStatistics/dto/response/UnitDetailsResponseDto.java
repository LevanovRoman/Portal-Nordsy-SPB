package com.myapp.portalnordsyspb.trainingStatistics.dto.response;

import java.util.List;

public record UnitDetailsResponseDto(
    int weekNumber,
    String date,
    List<Integer> values,
    String directionName,
    List<PersonResponseDto> persons
) {
}
