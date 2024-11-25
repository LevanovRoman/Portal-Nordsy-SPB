package com.myapp.portalnordsyspb.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto {
    private long id;
    private String title;
//    private long sectionId;
//    private String fileName;
    private String fileUrl;
}
