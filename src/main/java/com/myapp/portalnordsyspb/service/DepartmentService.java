package com.myapp.portalnordsyspb.service;

import com.myapp.portalnordsyspb.dto.responseDto.DepartmentTableDto;
import com.myapp.portalnordsyspb.entities.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartments();

//    List<DepartmentDto> getListDepartments();

    List<DepartmentTableDto> getListDepartmentTable();

//    void createTotalWeekSet(TotalWeekSetDto totalWeekSetDto);

//    void createDepartmentSet(DepartmentRequestDto departmentRequestDto);

//    List<DepartmentWeekDto> getListDepartmentsByWeek(int weekNumber);

//    void addTotalWeekSet(int weekNumber, TotalWeekSetDto totalWeekSetDto);
}
