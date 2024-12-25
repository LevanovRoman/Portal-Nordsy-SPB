package com.myapp.portalnordsyspb.trainingStatistics.dto.response;

import java.time.LocalDate;
import java.util.List;

public record UnitDetailsResponseDto(
    int weekNumber,
    boolean completed,
    LocalDate date,
    List<Integer> values,
    String directionName,
    List<PersonResponseDto> persons,
    List<String> tubNumbersList
) {
}
