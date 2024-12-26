package com.myapp.portalnordsyspb.suggestionForImprovement.dto.response;

public record SuggestionDirectorResponseDto(
        String position,
        int registered,
        int implemented,
        int inclusion
) {
}
