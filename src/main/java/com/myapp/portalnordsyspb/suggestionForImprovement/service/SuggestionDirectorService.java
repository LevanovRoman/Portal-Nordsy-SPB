package com.myapp.portalnordsyspb.suggestionForImprovement.service;

import com.myapp.portalnordsyspb.suggestionForImprovement.dto.response.StatisticsDirectorsResponseDto;

import java.util.List;

public interface SuggestionDirectorService {

    List<StatisticsDirectorsResponseDto> getAllDirectors();
}
