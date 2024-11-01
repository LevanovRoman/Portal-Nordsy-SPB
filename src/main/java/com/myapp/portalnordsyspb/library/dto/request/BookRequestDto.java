package com.myapp.portalnordsyspb.library.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDto {
    private String title;
    private long sectionId;
    private String fileName;
    private String fileUrl;
}


