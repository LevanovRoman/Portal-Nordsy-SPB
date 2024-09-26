package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.level5S.dto.test.Area5SiteDto;

import java.util.List;

public interface Area5SService {
//    List<Area5STableDto> getListArea5STableDtoByDepartmentId(Long id);
//
//    Optional<Area5S> getArea5SById(Long area_id);

    List<Area5SiteDto> getAllArea5SDto(Long month_id);
}
