package com.myapp.portalnordsyspb.evaluationPU.dto.responseDto;

import com.myapp.portalnordsyspb.evaluationPU.entity.Area;

import java.util.List;

public record AreaAndCriterionDto(
        List<AreaDto> areas,
        List<CriterionDto> criterions
) {
}
