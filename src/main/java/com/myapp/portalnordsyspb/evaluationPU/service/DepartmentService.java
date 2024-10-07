package com.myapp.portalnordsyspb.evaluationPU.service;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.AreaAndCriterionDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.DepartmentTableDto;
import com.myapp.portalnordsyspb.evaluationPU.entity.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartments();

//    List<DepartmentDto> getListDepartments();

    List<DepartmentTableDto> getListDepartmentTable();

    AreaAndCriterionDto getListAreaDtoAndCriterionDto();

//    void createTotalWeekSet(TotalWeekSetDto totalWeekSetDto);

//    void createDepartmentSet(DepartmentRequestDto departmentRequestDto);

//    List<DepartmentWeekDto> getListDepartmentsByWeek(int weekNumber);

//    void addTotalWeekSet(int weekNumber, TotalWeekSetDto totalWeekSetDto);
}
