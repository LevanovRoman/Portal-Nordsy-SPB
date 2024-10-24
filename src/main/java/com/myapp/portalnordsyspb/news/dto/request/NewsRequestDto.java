package com.myapp.portalnordsyspb.news.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsRequestDto {

    private String title;

    private String content;

//    private List<Category> categoryList;
//    private List<String> categoryListString;

    private Set<String> hashtags;

    private String photo;

    private String photoUrl;
}
