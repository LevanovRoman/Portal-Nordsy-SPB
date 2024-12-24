package com.myapp.portalnordsyspb.inspectionCEO.dto.response;

import com.myapp.portalnordsyspb.inspectionCEO.entity.ScoreColor;

public record InspectionResponseDto(
        int department,
        String date,
        ScoreColor totalScore,
        ScoreColor generalScore
) {
}
