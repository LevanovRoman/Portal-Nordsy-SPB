package com.myapp.portalnordsyspb.dto.requestDto;

import java.util.List;

public record TotalWeekSetDto(

        int week,

        List<DepartmentRequestDto> departmentRequestDtoList

) {
}
