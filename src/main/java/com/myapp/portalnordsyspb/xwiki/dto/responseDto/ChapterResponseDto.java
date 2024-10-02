package com.myapp.portalnordsyspb.xwiki.dto.responseDto;

import java.util.List;

public record ChapterResponseDto(
        Long chapterId,
        String name,
        List<PostResponseDto> posts
) { }
