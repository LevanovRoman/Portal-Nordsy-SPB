package com.myapp.portalnordsyspb.evaluationPU.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record AreaTableDto(
        String name,
        List<ResultTableLastWeekDto>resultLastWeekDtoList,
        List<ResultTableFourWeeksDto> resultTotalFourWeeksDtoList
) {
}
