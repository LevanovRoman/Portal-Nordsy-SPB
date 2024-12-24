package com.myapp.portalnordsyspb.inspectionCEO.dto.request;

import com.myapp.portalnordsyspb.inspectionCEO.entity.ScoreColor;

public record InspectionRequestDto(
        long id,
        String date,
        ScoreColor totalScore,
        ScoreColor generalScore
) {
}
