package com.myapp.portalnordsyspb.evaluationPU.service;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.AreaDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.AreaTableDto;
import com.myapp.portalnordsyspb.evaluationPU.entity.Area;

import java.util.List;
import java.util.Optional;

public interface AreaService {

    List<AreaTableDto> getListAreaTableDtoByDepartmentId(Long departmentId);

    Optional<Area> getAreaById(Long area_id);

    List<AreaDto> getAreaDto();

//    AreaDto getAreaDtoById(Long id);

//    List<AreaDto> getListAreasByDepartmentId(Long departmentId);

//    List<AreaWeekDto> getListAreasByDepartmentIdAndWeek(Long id, int weekNumber);



//    List<Area> createAreaWeekSet(List<AreaRequestDto> areaRequestDtoList);
}
