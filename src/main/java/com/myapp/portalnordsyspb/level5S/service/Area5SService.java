package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.evaluationPU.entity.Area;
import com.myapp.portalnordsyspb.level5S.dto.Area5STableDto;
import com.myapp.portalnordsyspb.level5S.entity.Area5S;

import java.util.List;
import java.util.Optional;

public interface Area5SService {
    List<Area5STableDto> getListArea5STableDtoByDepartmentId(Long id);

    Optional<Area5S> getArea5SById(Long area_id);
}
