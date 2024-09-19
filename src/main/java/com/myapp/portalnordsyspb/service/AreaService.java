package com.myapp.portalnordsyspb.service;

import com.myapp.portalnordsyspb.dto.responseDto.AreaTableDto;
import com.myapp.portalnordsyspb.entities.Area;

import java.util.List;
import java.util.Optional;

public interface AreaService {

    List<AreaTableDto> getListAreaTableDtoByDepartmentId(Long departmentId);

    Optional<Area> getAreaById(Long area_id);

//    AreaDto getAreaDtoById(Long id);

//    List<AreaDto> getListAreasByDepartmentId(Long departmentId);

//    List<AreaWeekDto> getListAreasByDepartmentIdAndWeek(Long id, int weekNumber);



//    List<Area> createAreaWeekSet(List<AreaRequestDto> areaRequestDtoList);
}
