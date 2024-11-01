package com.myapp.portalnordsyspb.library.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SectionRequestDto {
    private String name;
    private String label;
    private String labelUrl;

}
