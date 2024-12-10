package com.myapp.portalnordsyspb.trainingStatistics.dto.response;

import java.util.List;

public record PeriodResponseDto(
        long id,
        String interval,
        String month,
        int number,
        List<DirectionResponseDto> directions
){
}
