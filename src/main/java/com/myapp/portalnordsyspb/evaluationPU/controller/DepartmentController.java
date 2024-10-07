package com.myapp.portalnordsyspb.evaluationPU.controller;

import com.myapp.portalnordsyspb.evaluationPU.dto.requestDto.ResultRequestDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.AreaAndCriterionDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.DepartmentTableDto;
import com.myapp.portalnordsyspb.evaluationPU.entity.Department;
import com.myapp.portalnordsyspb.evaluationPU.service.DepartmentService;
import com.myapp.portalnordsyspb.evaluationPU.service.ResultService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    private final ResultService resultService;

    @Operation(summary = "Просмотр всех цехов")
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/list/site")
    public ResponseEntity<List<DepartmentTableDto>> getListDepartmentTable(){
        return ResponseEntity.ok(departmentService.getListDepartmentTable());
    }

    @PostMapping("/create-week-result")
    public ResponseEntity<?> createResultForWeek(@RequestBody List<ResultRequestDto> resultRequestDtoList){
        resultService.addResultsForWeek(resultRequestDtoList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/areas-and-criterions")
    public ResponseEntity<AreaAndCriterionDto> getListAreaDtoAndCriterionDto(){
        return ResponseEntity.ok(departmentService.getListAreaDtoAndCriterionDto());

    }

}

