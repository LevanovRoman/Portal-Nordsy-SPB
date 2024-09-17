package com.myapp.portalnordsyspb.service;

import com.myapp.portalnordsyspb.dto.DepartmentDto;
import com.myapp.portalnordsyspb.dto.DepartmentWeekDto;
import com.myapp.portalnordsyspb.dto.requestDto.TotalWeekSetDto;
import com.myapp.portalnordsyspb.entities.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartments();

    List<DepartmentDto> getListDepartments();

    List<DepartmentWeekDto> getListDepartmentsByWeek(int weekNumber);

//    void addTotalWeekSet(int weekNumber, TotalWeekSetDto totalWeekSetDto);
}
