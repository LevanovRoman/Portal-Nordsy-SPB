package com.myapp.portalnordsyspb.suggestionForImprovement.dto.response;

public record StatisticsResponseDto(
        int registered,
        int agreed,
        int implemented,
        int inclusion,
        int categoryImprovements,
        int categoryErgonomics,
        int categoryProcesses,
        int categoryProtection,
        int categoryTechnology
) {
}
