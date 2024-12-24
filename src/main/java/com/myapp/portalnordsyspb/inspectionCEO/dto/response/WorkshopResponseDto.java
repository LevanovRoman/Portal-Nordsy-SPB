package com.myapp.portalnordsyspb.inspectionCEO.dto.response;

import java.util.List;

public record WorkshopResponseDto(
        int number,
        List<InspectionResponseDto> inspectionResponseDtoList
) {
}
