package com.myapp.portalnordsyspb.level5S.controller;

import com.myapp.portalnordsyspb.evaluationPU.dto.requestDto.DepartmentRequestDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.level5S.dto.request.Department5SRequestDto;
import com.myapp.portalnordsyspb.level5S.service.Department5SService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/level-5c")
@Tag(name = "Area & Department 5C", description = "Description for it")
@RequiredArgsConstructor
public class Department5SController {

    private final Department5SService department5SService;

    @PostMapping("/create-department5S")
    @Operation(summary = "Создание нового цеха",
            description = "Creating new  department. The response is a string about success.")
    public ResponseEntity<MessageDto> createDepartment5S(@RequestBody Department5SRequestDto department5SRequestDto){
        department5SService.createDepartment5S(department5SRequestDto);
        return ResponseEntity.ok(new MessageDto("Department created successfully"));
    }

    @PutMapping("/update-department5S/{departmentId}")
    @Operation(summary = "Редактирование цеха",
            description = "Updating department by specifying its id. The response is a string about success.")
    public ResponseEntity<MessageDto> updateDepartment5S(@RequestBody Department5SRequestDto department5SRequestDto,
                                                       @PathVariable("departmentId") long departmentId){
        department5SService.updateDepartment5S(department5SRequestDto, departmentId);
        return ResponseEntity.ok(new MessageDto("Department updated successfully"));
    }

    @DeleteMapping("/delete-department5S/{departmentId}")
    @Operation(summary = "Удаление цеха",
            description = "Deleting department by specifying its id. The response is a string about success.")
    public ResponseEntity<MessageDto> deleteDepartment5S(@PathVariable("departmentId") long departmentId){
        department5SService.deleteDepartment5S(departmentId);
        return ResponseEntity.ok(new MessageDto("Department deleted successfully"));
    }
}
