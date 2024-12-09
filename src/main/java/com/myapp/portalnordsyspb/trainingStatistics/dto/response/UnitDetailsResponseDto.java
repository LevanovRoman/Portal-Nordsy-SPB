package com.myapp.portalnordsyspb.trainingStatistics.dto.response;

public record UnitDetailsResponseDto(
    int weekNumber,
    String date,
    int unitValue,
    String directionName,
    String tabNumber,
    String fullName,
    String position
) {
}
