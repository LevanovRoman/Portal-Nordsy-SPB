package com.myapp.portalnordsyspb.suggestionForImprovement.service;

import com.myapp.portalnordsyspb.suggestionForImprovement.dto.response.StatisticsDirectorsResponseDto;
import com.myapp.portalnordsyspb.suggestionForImprovement.dto.response.StatisticsInvolvedForDiagram;

import java.util.List;

public interface SuggestionDirectorService {

    List<StatisticsDirectorsResponseDto> getAllDirectors();
    List<StatisticsInvolvedForDiagram> getDataForDiagramInvolvedPerDepartment();
}
