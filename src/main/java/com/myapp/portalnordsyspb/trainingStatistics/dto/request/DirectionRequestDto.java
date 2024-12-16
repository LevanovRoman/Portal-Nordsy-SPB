package com.myapp.portalnordsyspb.trainingStatistics.dto.request;

import java.util.List;

public record DirectionRequestDto(
        String name,
        String remark,
        float hours,
        List<Long> instructorIdList
) {
}
