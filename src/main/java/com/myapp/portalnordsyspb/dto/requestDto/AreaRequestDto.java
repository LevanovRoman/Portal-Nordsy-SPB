package com.myapp.portalnordsyspb.dto.requestDto;

import java.util.List;

public record AreaRequestDto(
        String name,
        List<ResultRequestDto> resultWeekList

) {
}
