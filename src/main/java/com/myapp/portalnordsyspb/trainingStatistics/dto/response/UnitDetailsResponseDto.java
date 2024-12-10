package com.myapp.portalnordsyspb.trainingStatistics.dto.response;

import java.util.List;

public record UnitDetailsResponseDto(
    int weekNumber,
    String date,
    int unitValue,
    String directionName,
    List<PersonResponseDto> persons
) {
}
