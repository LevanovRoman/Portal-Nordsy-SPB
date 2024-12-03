package com.myapp.portalnordsyspb.trainingStatistics.controller;

import com.myapp.portalnordsyspb.trainingStatistics.dto.response.PeriodResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.service.PeriodService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/training")
@Tag(name = "Training Statistics", description = "Description for Training Statistics")
@RequiredArgsConstructor
public class PeriodController {

    private final PeriodService periodService;

    @GetMapping("/all")
    public ResponseEntity<List<PeriodResponseDto>> getAllPeriodResponseDto(){
        return ResponseEntity.ok(periodService.getAllPeriodResponseDto());
    }
}
