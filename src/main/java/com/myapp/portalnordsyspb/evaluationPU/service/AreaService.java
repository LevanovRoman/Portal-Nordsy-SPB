package com.myapp.portalnordsyspb.evaluationPU.service;

import com.myapp.portalnordsyspb.evaluationPU.dto.requestDto.AreaRequestDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.AreaDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.AreaTableDto;
import com.myapp.portalnordsyspb.evaluationPU.entity.Area;

import java.util.List;
import java.util.Optional;

public interface AreaService {

    List<AreaTableDto> getListAreaTableDtoByDepartmentId(Long departmentId);

    List<AreaDto> getAreaDto();

    void createArea(AreaRequestDto areaRequestDto);

    void updateArea(AreaRequestDto areaRequestDto, long areaId);

    void deleteArea(long areaId);
}
