package com.myapp.portalnordsyspb.suggestionForImprovement.dto.response;

public record StatisticsDirectorsResponseDto(
        String position,
        int registered,
        int implemented,
        int inclusion
) {
}
