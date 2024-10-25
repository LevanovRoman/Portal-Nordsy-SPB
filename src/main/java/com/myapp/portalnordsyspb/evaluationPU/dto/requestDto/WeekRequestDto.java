package com.myapp.portalnordsyspb.evaluationPU.dto.requestDto;

import java.util.List;

public record WeekRequestDto(
        String week,
        List<ResultRequestDto> results
) {
}
