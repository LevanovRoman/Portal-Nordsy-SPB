package com.myapp.portalnordsyspb.suggestionForImprovement.service;

import com.myapp.portalnordsyspb.suggestionForImprovement.dto.response.SuggestionAllResponseDto;

import java.util.List;

public interface SuggestionService {

    List<SuggestionAllResponseDto> getAllSuggestion();
}
