package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.level5S.dto.request.Department5SRequestDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Month5SAverageDto;

public interface Department5SService {

    Month5SAverageDto getListLevelDoneForDepartment();

    void createDepartment5S(Department5SRequestDto department5SRequestDto);

    void updateDepartment5S(Department5SRequestDto department5SRequestDto, long departmentId);

    void deleteDepartment5S(long departmentId);
}
