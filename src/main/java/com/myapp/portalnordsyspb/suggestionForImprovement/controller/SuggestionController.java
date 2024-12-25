package com.myapp.portalnordsyspb.suggestionForImprovement.controller;

import com.myapp.portalnordsyspb.suggestionForImprovement.dto.response.SuggestionAllResponseDto;
import com.myapp.portalnordsyspb.suggestionForImprovement.service.SuggestionService;
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
public class SuggestionController {

    private final SuggestionService suggestionService;

    @GetMapping("/all")
    public ResponseEntity<List<SuggestionAllResponseDto>> getAllSuggestions(){
        return ResponseEntity.ok(suggestionService.getAllSuggestion());
    }
}
