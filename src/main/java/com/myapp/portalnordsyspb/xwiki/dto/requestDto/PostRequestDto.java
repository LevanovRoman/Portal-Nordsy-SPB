package com.myapp.portalnordsyspb.xwiki.dto.requestDto;

public record PostRequestDto(
        String title,
        String description,
        long chapterId
) {
}
