package com.myapp.portalnordsyspb.evaluationPU.dto.responseDto;

import java.util.List;

public record DepartmentTableDto(
        int department,
        List<AreaTableDto> areaSiteDtoList
) {
}
