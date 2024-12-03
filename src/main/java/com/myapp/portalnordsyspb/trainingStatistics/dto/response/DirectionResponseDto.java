package com.myapp.portalnordsyspb.trainingStatistics.dto.response;

import java.util.List;

public record DirectionResponseDto(
        String name,
        int size,
        String remark,
        float hours,
        List<InstructorResponseDto> instructors,
        UnitResponseDto units
) {
}
