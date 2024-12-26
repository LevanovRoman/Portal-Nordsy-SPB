package com.myapp.portalnordsyspb.suggestionForImprovement.service;

import com.myapp.portalnordsyspb.suggestionForImprovement.dto.response.SuggestionDirectorResponseDto;

import java.util.List;

public interface SuggestionDirectorService {

    List<SuggestionDirectorResponseDto> getAllSuggestionDirectors();
}
