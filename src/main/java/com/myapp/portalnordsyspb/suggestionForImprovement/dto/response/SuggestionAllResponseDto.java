package com.myapp.portalnordsyspb.suggestionForImprovement.dto.response;

public record SuggestionAllResponseDto(
        long id,
        String title,
        String numberAndDateRegistration,
        String department,
        String author,
        boolean registered,
        boolean agreed,
        boolean implemented,
        String dateImplementation
) {
}
