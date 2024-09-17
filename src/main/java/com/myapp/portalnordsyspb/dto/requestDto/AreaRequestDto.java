package com.myapp.portalnordsyspb.dto.requestDto;

import com.myapp.portalnordsyspb.dto.ResultWeekDto;

import java.util.List;

public record AreaRequestDto(
        String name,
        List<ResultRequestDto> resultWeekList

) {
}
