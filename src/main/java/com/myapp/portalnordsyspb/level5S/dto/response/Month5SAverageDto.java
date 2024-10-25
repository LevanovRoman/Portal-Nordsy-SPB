package com.myapp.portalnordsyspb.level5S.dto.response;

import java.util.List;

public record Month5SAverageDto(
        String monthAndYear,
        List<Department5SDto> results
) {
}
