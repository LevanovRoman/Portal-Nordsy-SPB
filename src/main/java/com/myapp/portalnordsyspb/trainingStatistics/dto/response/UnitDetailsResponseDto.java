package com.myapp.portalnordsyspb.trainingStatistics.dto.response;

import java.util.List;

public record UnitDetailsResponseDto(
    int weekNumber,
    boolean completed,
    String date,
    List<Integer> values,
    String directionName,
    List<PersonResponseDto> persons
) {
}
