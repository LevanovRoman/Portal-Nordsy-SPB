package com.myapp.portalnordsyspb.trainingStatistics.controller;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.PeriodRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.PeriodResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.service.PeriodService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training/period")
@Tag(name = "Training Statistics", description = "Description for Training Statistics")
@RequiredArgsConstructor
public class PeriodController {

    private final PeriodService periodService;

    @GetMapping("/all")
    public ResponseEntity<List<PeriodResponseDto>> getAllPeriodResponseDto(){
        return ResponseEntity.ok(periodService.getAllPeriodResponseDto());
    }

    @PostMapping("/create-period")
    public ResponseEntity<MessageDto> createPeriod(@RequestBody PeriodRequestDto periodRequestDto){
        periodService.createPeriod(periodRequestDto);
        return ResponseEntity.ok(new MessageDto("Period created successfully."));
    }

    @PutMapping("/update-period/{periodId}")
    public ResponseEntity<MessageDto> updatePeriod(@RequestBody PeriodRequestDto periodRequestDto,
                                                   @PathVariable("periodId") long periodId){
        periodService.updatePeriod(periodRequestDto, periodId);
        return ResponseEntity.ok(new MessageDto("Period updated successfully."));
    }

    @DeleteMapping("/delete-period/{periodId}")
    public ResponseEntity<MessageDto> deletePeriod(@PathVariable("periodId") long periodId){
        periodService.deletePeriod(periodId);
        return ResponseEntity.ok(new MessageDto("Period deleted successfully."));
    }
}
