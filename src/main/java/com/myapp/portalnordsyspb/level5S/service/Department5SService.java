package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.level5S.dto.response.Department5SDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Month5SAverageDto;

import java.util.List;

public interface Department5SService {

    Month5SAverageDto getListLevelDoneForDepartment();
}
