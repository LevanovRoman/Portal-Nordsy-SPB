package com.myapp.portalnordsyspb.evaluationPU.controller;

import com.myapp.portalnordsyspb.evaluationPU.dto.requestDto.ResultRequestDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.*;
import com.myapp.portalnordsyspb.evaluationPU.entity.Department;
import com.myapp.portalnordsyspb.evaluationPU.service.DepartmentService;
import com.myapp.portalnordsyspb.evaluationPU.service.ResultService;
import com.myapp.portalnordsyspb.evaluationPU.service.WeekService;
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

    private final WeekService weekService;

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
    public ResponseEntity<MessageDto> createResultForWeek(@RequestBody List<ResultRequestDto> resultRequestDtoList){
        resultService.addResultsForWeek(resultRequestDtoList);
        return new ResponseEntity<>(new MessageDto("Result for week created successfully"), HttpStatus.CREATED);
    }

    @PutMapping("/update-week-result/{weekId}")
    public ResponseEntity<MessageDto> updateResultForWeek(@RequestBody List<ResultRequestDto> resultRequestDtoList,
                                                          @PathVariable("weekId") long weekId){
        resultService.updateResultsForWeek(resultRequestDtoList, weekId);
        return new ResponseEntity<>(new MessageDto("Result for week updated successfully"), HttpStatus.OK);
    }

    @DeleteMapping("/delete-week-result/{weekId}")
    public ResponseEntity<MessageDto> deleteResultForWeek(@PathVariable("weekId") long weekId){
        resultService.deleteResultForWeek(weekId);
        return new ResponseEntity<>(new MessageDto("Result for week deleted successfully"), HttpStatus.OK);
    }

    @GetMapping("/areas-and-criterions")
    public ResponseEntity<AreaAndCriterionDto> getListAreaDtoAndCriterionDto(){
        return ResponseEntity.ok(departmentService.getListAreaDtoAndCriterionDto());
    }

    @GetMapping("/all-weeks")
    public ResponseEntity<List<WeekDto>> getListWeekDto(){
        return ResponseEntity.ok(weekService.getListWeekDto());
    }

    @GetMapping("/week-result/{weekId}")
    public ResponseEntity<List<ResultResponseDto>> getListResultResponseDtoForWeek(@PathVariable("weekId") long weekId){
        return ResponseEntity.ok(resultService.getListResultResponseDtoForWeek(weekId));
    }

}

