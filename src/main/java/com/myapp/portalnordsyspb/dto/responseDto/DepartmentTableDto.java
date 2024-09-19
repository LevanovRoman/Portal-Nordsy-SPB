package com.myapp.portalnordsyspb.dto.responseDto;

import java.util.List;

public record DepartmentTableDto(
        int department,
        List<AreaTableDto> areaSiteDtoList
) {
}
