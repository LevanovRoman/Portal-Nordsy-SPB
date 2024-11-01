package com.myapp.portalnordsyspb.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SectionResponseDto {
    private long id;
    private String name;
    private String label;
    private String labelUrl;
}
