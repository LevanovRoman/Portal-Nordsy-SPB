package com.myapp.portalnordsyspb.controllers;

import com.myapp.portalnordsyspb.dto.DepartmentDto;
import com.myapp.portalnordsyspb.dto.DepartmentWeekDto;
import com.myapp.portalnordsyspb.dto.requestDto.TotalWeekSetDto;
import com.myapp.portalnordsyspb.entities.Department;
import com.myapp.portalnordsyspb.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @Operation(summary = "Просмотр всех цехов")
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }
    @GetMapping("/list")
    public ResponseEntity<List<DepartmentDto>> getListDepartments(){
        return ResponseEntity.ok(departmentService.getListDepartments());
    }

    @GetMapping("/list/{weekNumber}")
    public ResponseEntity<List<DepartmentWeekDto>> getListDepartmentsByWeek(@PathVariable int weekNumber){
        return ResponseEntity.ok(departmentService.getListDepartmentsByWeek(weekNumber));
    }

//    @PostMapping("/{weekNumber}")
//    public ResponseEntity<?> addTotalWeekSet(@PathVariable int weekNumber,
//                                             @RequestBody TotalWeekSetDto totalWeekSetDto){
//        return ResponseEntity.ok(departmentService.addTotalWeekSet(weekNumber, totalWeekSetDto));
//    }

}

