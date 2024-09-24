package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.level5S.dto.Department5STableDto;
import com.myapp.portalnordsyspb.level5S.entity.Department5S;

import java.util.List;

public interface Department5SService {
    List<Department5S> getAllDepartments();

    List<Department5STableDto> getListDepartmentTable();
}
