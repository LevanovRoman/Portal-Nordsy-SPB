package com.myapp.portalnordsyspb.news.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponseDto {
    private long id;

    private String title;

    private String content;

    private Set<String> hashtags;

    private String photo;

    private String photoUrl;
}
