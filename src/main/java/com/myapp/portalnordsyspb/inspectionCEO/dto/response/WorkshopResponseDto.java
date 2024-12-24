package com.myapp.portalnordsyspb.inspectionCEO.dto.response;

import java.util.List;

public record WorkshopResponseDto(
        long workshopId,
        int number,
        List<InspectionResponseDto> inspectionResponseDtoList
) {
}
