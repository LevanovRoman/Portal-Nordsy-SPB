package com.myapp.portalnordsyspb.inspectionCEO.dto.response;

import com.myapp.portalnordsyspb.inspectionCEO.entity.ScoreColor;

public record InspectionResponseDto(
        long id,
        int department,
        String date,
        ScoreColor totalScore,
        ScoreColor generalScore
) {
}
