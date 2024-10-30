package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.level5S.dto.request.Department5SRequestDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Department5SResponseDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Month5SAverageDto;

import java.util.List;

public interface Department5SService {

    Month5SAverageDto getListLevelDoneForDepartment();

    void createDepartment5S(Department5SRequestDto department5SRequestDto);

    void updateDepartment5S(Department5SRequestDto department5SRequestDto, long departmentId);

    void deleteDepartment5S(long departmentId);

    List<Department5SResponseDto> getAllDepartment5S();
}
