package com.myapp.portalnordsyspb.trainingStatistics.dto.response;

public record DiagramResponseDto(
        long id,
        int monthNumber,
        int quantityPersons,
        int quantityPersonsAggregate
) {
}
