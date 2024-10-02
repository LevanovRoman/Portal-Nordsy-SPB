package com.myapp.portalnordsyspb.xwiki.dto.responseDto;

public record PostResponseDto(
        Long postId,
        String title,
        String description
) {
}
