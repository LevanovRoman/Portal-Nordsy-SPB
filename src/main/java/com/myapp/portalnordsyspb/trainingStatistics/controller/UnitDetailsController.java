package com.myapp.portalnordsyspb.trainingStatistics.controller;

import com.myapp.portalnordsyspb.trainingStatistics.dto.response.UnitDetailsResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.service.UnitDetailsService;
import com.myapp.portalnordsyspb.trainingStatistics.service.UnitDetailsServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
