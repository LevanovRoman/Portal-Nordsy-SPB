package com.myapp.portalnordsyspb.level5S.dto;
import java.util.List;

public record Area5STableDto(
        String name,
        List<Result5STableDto> result5STableDtoList
) {
}
