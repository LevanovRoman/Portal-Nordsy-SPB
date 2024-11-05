package com.myapp.portalnordsyspb.evaluationPU.controller;

import com.myapp.portalnordsyspb.evaluationPU.dto.requestDto.DepartmentRequestDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.*;
import com.myapp.portalnordsyspb.evaluationPU.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@Tag(name = "Area & Department PU", description = "Description for table PU")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @Operation(summary = "Просмотр всех цехов",
            description = "Getting all departments. The response is list of objects with id and number.")
    @GetMapping("/all-departments")
    public ResponseEntity<List<DepartmentResponseDto>> getAllDepartments(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
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

