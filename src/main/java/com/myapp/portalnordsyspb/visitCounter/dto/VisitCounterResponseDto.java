package com.myapp.portalnordsyspb.visitCounter.dto;

public record VisitCounterResponseDto(
        int actualValue,
        int valueForYesterday,
        int valueForLastWeek,
        int valueForLastMonth
) {
}
