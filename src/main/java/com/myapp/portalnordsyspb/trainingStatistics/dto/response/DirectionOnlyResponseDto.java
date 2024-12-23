package com.myapp.portalnordsyspb.trainingStatistics.dto.response;

import java.util.List;

public record DirectionOnlyResponseDto(
        long directionId,
        String name,
        String remark,
        float hours,
        List<InstructorResponseDto> instructors
) {
}
