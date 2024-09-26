package com.myapp.portalnordsyspb.level5S.controller;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.DepartmentTableDto;
import com.myapp.portalnordsyspb.evaluationPU.entity.Department;
import com.myapp.portalnordsyspb.evaluationPU.service.DepartmentService;
import com.myapp.portalnordsyspb.level5S.dto.Department5STableDto;
import com.myapp.portalnordsyspb.level5S.entity.Department5S;
import com.myapp.portalnordsyspb.level5S.service.Department5SService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/department5s")
@RequiredArgsConstructor
public class Department5SController {

    private final Department5SService department5SService;

    @Operation(summary = "Просмотр всех цехов")
    @GetMapping
    public ResponseEntity<List<Department5S>> getAllDepartments(){
        return ResponseEntity.ok(department5SService.getAllDepartments());
    }

//    @GetMapping("/list")
//    public ResponseEntity<List<Department5STableDto>> getListDepartmentTable(){
//        return ResponseEntity.ok(department5SService.getListDepartment5STable());
//    }

}
