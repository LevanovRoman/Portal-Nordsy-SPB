package com.myapp.portalnordsyspb.evaluationPU.controller;

import com.myapp.portalnordsyspb.evaluationPU.dto.requestDto.ResultRequestDto;
import com.myapp.portalnordsyspb.evaluationPU.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/result")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;
//    private final WeekRepository weekRepository;

//    @Operation(summary = "Просмотр всех результатов")
//    @GetMapping
//    public ResponseEntity<List<Result>> getAllResults(){
//        return ResponseEntity.ok(resultService.getAllResults());
//    }
//
//    @GetMapping("/area/{areaId}")
//    public ResponseEntity<List<ResultDto>> getAllResultsByAreaId(@PathVariable long areaId){
//        return ResponseEntity.ok(resultService.getResultListByAreaId(areaId));
//    }

//    @GetMapping("/area/{areaId}")
//    public ResponseEntity<List<Result>> getAllResultsByAreaId(@PathVariable long areaId){
//        return ResponseEntity.ok(resultService.getResultFor2Week(areaId));
//    }

//    @GetMapping("/area")
//    public ResponseEntity<List<Week>> getAllResultsByAreaId(){
//        return ResponseEntity.ok(weekRepository.findTop4ByOrderByIdDesc());
//    }

    @PostMapping
    public ResponseEntity<?> createResultForWeek(@RequestBody List<ResultRequestDto> resultRequestDtoList){
        resultService.addResultsForWeek(resultRequestDtoList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}