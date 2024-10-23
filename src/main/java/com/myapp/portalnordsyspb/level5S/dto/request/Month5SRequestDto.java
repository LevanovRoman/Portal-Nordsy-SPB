package com.myapp.portalnordsyspb.level5S.dto.request;

import java.util.List;

public record Month5SRequestDto(
        String monthAndYear,
        List<Result5SRequestDto> results
) {
}
