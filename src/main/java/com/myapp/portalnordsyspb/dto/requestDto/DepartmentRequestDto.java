package com.myapp.portalnordsyspb.dto.requestDto;

import com.myapp.portalnordsyspb.dto.AreaDto;

import java.util.List;

public record DepartmentRequestDto(

        int department,
        List<AreaRequestDto> area
) {
}
