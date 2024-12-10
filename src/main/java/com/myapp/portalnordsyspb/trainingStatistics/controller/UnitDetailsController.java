package com.myapp.portalnordsyspb.trainingStatistics.controller;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.UnitDetailsRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.UnitDetailsResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.service.UnitDetailsService;
import com.myapp.portalnordsyspb.trainingStatistics.service.UnitDetailsServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create/{unitId}")
    public ResponseEntity<MessageDto> createUnitDetails(@RequestBody UnitDetailsRequestDto unitDetailsRequestDto,
                                                        @PathVariable long unitId){
        unitDetailsService.createUnitDetails(unitDetailsRequestDto, unitId);
        return ResponseEntity.ok(new MessageDto("UnitDetails created successfully."));
    }

    @PutMapping("/update/{unitDetailsId}")
    public ResponseEntity<MessageDto> updateUnitDetails(@RequestBody UnitDetailsRequestDto unitDetailsRequestDto,
                                                        @PathVariable long unitDetailsId){
        unitDetailsService.updateUnitDetails(unitDetailsRequestDto, unitDetailsId);
        return ResponseEntity.ok(new MessageDto("UnitDetails updated successfully."));
    }

    @DeleteMapping("/delete/{unitDetailsId}")
    public ResponseEntity<MessageDto> deleteUnitDetails(@PathVariable long unitDetailsId){
        unitDetailsService.deleteUnitDetails(unitDetailsId);
        return ResponseEntity.ok(new MessageDto("UnitDetails deleted successfully."));
    }
}
