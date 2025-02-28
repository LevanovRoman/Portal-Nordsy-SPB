package com.myapp.portalnordsyspb.suggestionForImprovement.controller;

import com.myapp.portalnordsyspb.suggestionForImprovement.dto.response.StatisticsDirectorsResponseDto;
import com.myapp.portalnordsyspb.suggestionForImprovement.dto.response.StatisticsInvolvedForDiagram;
import com.myapp.portalnordsyspb.suggestionForImprovement.dto.response.SuggestionAllResponseDto;
import com.myapp.portalnordsyspb.suggestionForImprovement.dto.response.SuggestionDirectorResponseDto;
import com.myapp.portalnordsyspb.suggestionForImprovement.service.SuggestionDirectorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/suggestion")
@Tag(name = "Suggestion For Improvement", description = "Description for Suggestion For Improvement")
@RequiredArgsConstructor
public class SuggestionDirectorController {

    private final SuggestionDirectorService suggestionDirectorService;

    @GetMapping("/directors/all")
    public ResponseEntity<List<StatisticsDirectorsResponseDto>> getAllDirectors(){
        return ResponseEntity.ok(suggestionDirectorService.getAllDirectors());
    }

    @GetMapping("/diagram-involved")
    public ResponseEntity<List<StatisticsInvolvedForDiagram>> getDataForDiagramInvolvedPerDepartment(){
        return ResponseEntity.ok(suggestionDirectorService.getDataForDiagramInvolvedPerDepartment());
    }
}
