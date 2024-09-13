package com.myapp.portalnordsyspb.controllers;

import com.myapp.portalnordsyspb.dto.ResultDto;
import com.myapp.portalnordsyspb.entities.Result;
import com.myapp.portalnordsyspb.service.ResultService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/result")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @Operation(summary = "Просмотр всех результатов")
    @GetMapping
    public ResponseEntity<List<Result>> getAllResults(){
        return ResponseEntity.ok(resultService.getAllResults());
    }

    @GetMapping("/area/{areaId}")
    public ResponseEntity<List<ResultDto>> getAllResultsByAreaId(@PathVariable long areaId){
        return ResponseEntity.ok(resultService.getResultListByAreaId(areaId));
    }

}