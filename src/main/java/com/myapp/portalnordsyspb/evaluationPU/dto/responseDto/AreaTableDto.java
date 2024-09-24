package com.myapp.portalnordsyspb.evaluationPU.dto.responseDto;

import java.util.List;

public record AreaTableDto(
        String name,
        List<ResultTableLastWeekDto>resultLastWeekDtoList,
        List<ResultTableFourWeeksDto> resultTotalFourWeeksDtoList
) {
}
