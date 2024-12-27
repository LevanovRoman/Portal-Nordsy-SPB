package com.myapp.portalnordsyspb.trainingStatistics.controller;

import com.myapp.portalnordsyspb.trainingStatistics.dto.response.DiagramResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.UnitDetailsResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.service.UnitDetailsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training/unit-details")
@Tag(name = "Training Statistics", description = "Description for Training Statistics")
@RequiredArgsConstructor
public class UnitDetailsController {

    private final UnitDetailsService unitDetailsService;

    @GetMapping("/{unitId}")
    public ResponseEntity<UnitDetailsResponseDto> getUnitDetailsByUnitId(@PathVariable("unitId") long unitId){
        return ResponseEntity.ok(unitDetailsService.getUnitDetailsByUnitId(unitId));
    }

    @GetMapping("/diagram/{year}")
    public ResponseEntity<List<DiagramResponseDto>> getDataForDiagram(@PathVariable("year") int year){
        return ResponseEntity.ok(unitDetailsService.getDataForDiagram(year));
    }
}
