package com.myapp.portalnordsyspb.evaluationPU.service;

import com.myapp.portalnordsyspb.evaluationPU.dto.requestDto.DepartmentRequestDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.AreaAndCriterionDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.DepartmentResponseDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.DepartmentTableDto;
import com.myapp.portalnordsyspb.evaluationPU.entity.Department;

import java.util.List;

public interface DepartmentService {

    List<DepartmentResponseDto> getAllDepartments();

    List<DepartmentTableDto> getListDepartmentTable();

    AreaAndCriterionDto getListAreaDtoAndCriterionDto();

    void createDepartment(DepartmentRequestDto departmentRequestDto);

    void updateDepartment(DepartmentRequestDto departmentRequestDto, long departmentId);

    void deleteDepartment(long departmentId);
}
