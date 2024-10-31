package com.myapp.portalnordsyspb.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentResponseDto{
    private long id;
    private String title;
    private String section;
    private String fileName;
    private String fileUrl;
}
