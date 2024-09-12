package com.myapp.portalnordsyspb.controllers;

import com.myapp.portalnordsyspb.dto.ResultDto;
import com.myapp.portalnordsyspb.entities.Department;
import com.myapp.portalnordsyspb.entities.Result;
import com.myapp.portalnordsyspb.service.ResultService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<List<ResultDto>> getAllResults(){
//        List<Result> resultList = resultService.getAllResults();
//        resultList.forEach(x -> System.out.println(x.getAreaCriterion().getCriterion().getName() + " " + x.getAreaCriterion().getArea().getName() + " " + x.getValue()));

        return ResponseEntity.ok(resultService.getResultList());
    }

}
