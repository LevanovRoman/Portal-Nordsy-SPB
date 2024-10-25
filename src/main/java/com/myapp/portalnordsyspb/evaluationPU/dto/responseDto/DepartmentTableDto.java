package com.myapp.portalnordsyspb.evaluationPU.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record DepartmentTableDto(
        int department,
        List<AreaTableDto> areaTableDtoList
) {
}
