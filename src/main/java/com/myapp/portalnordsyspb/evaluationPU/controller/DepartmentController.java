package com.myapp.portalnordsyspb.evaluationPU.controller;

import com.myapp.portalnordsyspb.evaluationPU.dto.requestDto.DepartmentRequestDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.requestDto.WeekRequestDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.*;
import com.myapp.portalnordsyspb.evaluationPU.entity.Department;
import com.myapp.portalnordsyspb.evaluationPU.service.DepartmentService;
import com.myapp.portalnordsyspb.evaluationPU.service.ResultService;
import com.myapp.portalnordsyspb.evaluationPU.service.WeekService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@Tag(name = "Table PU", description = "Description for table PU")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    private final ResultService resultService;

    private final WeekService weekService;

    @Operation(summary = "Просмотр всех цехов",
    description = "Getting all departments. The response is list of objects with id and number.")
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/list/site")
    public ResponseEntity<List<DepartmentTableDto>> getListDepartmentTable(){
        return ResponseEntity.ok(departmentService.getListDepartmentTable());
    }

    @PostMapping("/create-week-result")
    public ResponseEntity<MessageDto> createResultForWeek(@RequestBody WeekRequestDto weekRequestDto){
        resultService.createResultsForWeek(weekRequestDto);
        return new ResponseEntity<>(new MessageDto("Result for week created successfully"), HttpStatus.CREATED);
    }

    @PutMapping("/update-week-result/{weekId}")
    public ResponseEntity<MessageDto> updateResultForWeek(@RequestBody WeekRequestDto weekRequestDto,
                                                          @PathVariable("weekId") long weekId){
        resultService.updateResultsForWeek(weekRequestDto, weekId);
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
    @Operation(summary = "Просмотр всех недель",
            description = "Getting all weeks. The response is list of objects with id and week`s name.")
    public ResponseEntity<List<WeekDto>> getListWeekDto(){
        return ResponseEntity.ok(weekService.getListWeekDto());
    }

    @GetMapping("/week-result/{weekId}")
    @Operation(summary = "Просмотр результатов за конкретную неделю",
            description = "Getting results for a week by specifying its id. The response is list of objects with areaId," +
                    " criterionId and value.")
    public ResponseEntity<List<ResultResponseDto>> getListResultResponseDtoForWeek(@PathVariable("weekId") long weekId){
        return ResponseEntity.ok(resultService.getListResultResponseDtoForWeek(weekId));
    }

    @PostMapping("/create-department")
    @Operation(summary = "Создание нового цеха",
            description = "Creating new  department. The response is a string about success.")
    public ResponseEntity<MessageDto> createDepartment(@RequestBody DepartmentRequestDto departmentRequestDto){
        departmentService.createDepartment(departmentRequestDto);
        return ResponseEntity.ok(new MessageDto("Department created successfully"));
    }

    @PutMapping("/update-department/{departmentId}")
    @Operation(summary = "Редактирование цеха",
            description = "Updating department by specifying its id. The response is a string about success.")
    public ResponseEntity<MessageDto> updateDepartment(@RequestBody DepartmentRequestDto departmentRequestDto,
                                                       @PathVariable("departmentId") long departmentId){
        departmentService.updateDepartment(departmentRequestDto, departmentId);
        return ResponseEntity.ok(new MessageDto("Department updated successfully"));
    }

    @DeleteMapping("/delete-department/{departmentId}")
    @Operation(summary = "Удаление цеха",
            description = "Deleting department by specifying its id. The response is a string about success.")
    public ResponseEntity<MessageDto> deleteDepartment(@PathVariable("departmentId") long departmentId){
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.ok(new MessageDto("Department deleted successfully"));
    }

}

