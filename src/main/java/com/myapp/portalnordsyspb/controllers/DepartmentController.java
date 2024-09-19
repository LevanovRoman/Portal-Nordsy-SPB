package com.myapp.portalnordsyspb.controllers;

import com.myapp.portalnordsyspb.dto.DepartmentSiteDto;
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
//    @GetMapping("/list")
//    public ResponseEntity<List<DepartmentDto>> getListDepartments(){
//        return ResponseEntity.ok(departmentService.getListDepartments());
//    }

    @GetMapping("/list/site")
    public ResponseEntity<List<DepartmentSiteDto>> getListDepartmentSite(){
        return ResponseEntity.ok(departmentService.getListDepartmentSite());
    }

//    @GetMapping("/list/{weekNumber}")
//    public ResponseEntity<List<DepartmentWeekDto>> getListDepartmentsByWeek(@PathVariable int weekNumber){
//        return ResponseEntity.ok(departmentService.getListDepartmentsByWeek(weekNumber));
//    }

//    @PostMapping
//    public ResponseEntity<?> createTotalWeekSet(@RequestBody TotalWeekSetDto totalWeekSetDto){
//        departmentService.createTotalWeekSet(totalWeekSetDto);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<?> createTotalWeekSet(@RequestBody DepartmentRequestDto departmentRequestDto){
//        departmentService.createDepartmentSet(departmentRequestDto);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

}

