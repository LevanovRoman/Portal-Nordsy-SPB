package com.myapp.portalnordsyspb.news.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponseDto {

    private String title;

    private String content;

    private List<String> categoryListString;

    private String photo;

    private String photoUrl;

}
