package com.myapp.portalnordsyspb.evaluationPU.dto.requestDto;

public record ResultRequestDto(
        long areaId,
        long criterionId,
        int value
) {
}
