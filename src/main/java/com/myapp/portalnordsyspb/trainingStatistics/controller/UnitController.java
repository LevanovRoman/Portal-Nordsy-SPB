package com.myapp.portalnordsyspb.trainingStatistics.controller;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.UnitRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.service.UnitService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/training/unit")
@Tag(name = "Training Statistics", description = "Description for Training Statistics")
@RequiredArgsConstructor
public class UnitController {

    private final UnitService unitService;

    @PutMapping("/update/{unitId}")
    public ResponseEntity<MessageDto> updateUnit(@RequestBody UnitRequestDto unitRequestDto,
                                                   @PathVariable("unitId") long unitId){
        unitService.updateUnit(unitRequestDto, unitId);
        return ResponseEntity.ok(new MessageDto("Unit updated successfully."));
    }

    @DeleteMapping("/delete/{unitId}")
    public ResponseEntity<MessageDto> deletePeriod(@PathVariable("unitId") long unitId){
        unitService.deleteUnit(unitId);
        return ResponseEntity.ok(new MessageDto("Unit deleted successfully."));
    }
}
