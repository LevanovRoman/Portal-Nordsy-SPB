package com.myapp.portalnordsyspb.level5S.dto.response;

import java.util.List;

public record Area5SAndCriterion5SDto(
        List<Area5SDto> areas,
        List<Criterion5SDto> criterions
) {
}
