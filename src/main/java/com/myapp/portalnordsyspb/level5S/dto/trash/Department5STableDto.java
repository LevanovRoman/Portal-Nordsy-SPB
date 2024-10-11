package com.myapp.portalnordsyspb.level5S.dto.trash;

import java.util.List;

public record Department5STableDto(
        int department,
        List<Area5STableDto> areaSiteDtoList
) {
}
