package com.myapp.portalnordsyspb.service;

import com.myapp.portalnordsyspb.dto.AreaDto;
import com.myapp.portalnordsyspb.dto.AreaSiteDto;

import java.util.List;

public interface AreaService {

//    AreaDto getAreaDtoById(Long id);

//    List<AreaDto> getListAreasByDepartmentId(Long departmentId);

//    List<AreaWeekDto> getListAreasByDepartmentIdAndWeek(Long id, int weekNumber);

    List<AreaSiteDto> getListAreaSiteDtoByDepartmentId(Long departmentId);

//    List<Area> createAreaWeekSet(List<AreaRequestDto> areaRequestDtoList);
}
