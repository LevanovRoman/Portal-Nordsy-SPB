package com.myapp.portalnordsyspb.trainingStatistics.dto.request;

import java.util.List;
import java.util.Set;

public record DirectionRequestDtoUpdate(
        String name,
        String remark,
        float hours,
        Set<Long> instructorIdSet
) {
}
