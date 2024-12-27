package com.myapp.portalnordsyspb.trainingStatistics.dto.request;

import java.util.Set;

public record DirectionRequestDto(
        String name,
        String remark,
        float hours,
        Set<Long> instructorIdSet
) {
}
