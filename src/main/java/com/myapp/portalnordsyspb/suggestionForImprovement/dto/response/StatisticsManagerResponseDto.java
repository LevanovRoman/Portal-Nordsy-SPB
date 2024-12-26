package com.myapp.portalnordsyspb.suggestionForImprovement.dto.response;

public record StatisticsManagerResponseDto(
        String manager,
        int registered,
        int implemented,
        int inclusion
) {
}
