package com.myapp.portalnordsyspb.library.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentRequestDto{
    private String title;
    private String section;
    private String fileName;
    private String fileUrl;
}


