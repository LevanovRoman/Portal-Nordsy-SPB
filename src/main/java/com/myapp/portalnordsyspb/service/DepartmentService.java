package com.myapp.portalnordsyspb.service;

import com.myapp.portalnordsyspb.dto.DepartmentSiteDto;
import com.myapp.portalnordsyspb.entities.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartments();

//    List<DepartmentDto> getListDepartments();

    List<DepartmentSiteDto> getListDepartmentSite();

//    void createTotalWeekSet(TotalWeekSetDto totalWeekSetDto);

//    void createDepartmentSet(DepartmentRequestDto departmentRequestDto);

//    List<DepartmentWeekDto> getListDepartmentsByWeek(int weekNumber);

//    void addTotalWeekSet(int weekNumber, TotalWeekSetDto totalWeekSetDto);
}
