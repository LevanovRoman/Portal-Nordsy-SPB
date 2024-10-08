package com.myapp.portalnordsyspb.news.dto.request;

import com.myapp.portalnordsyspb.news.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsRequestDto {

    private String title;

    private String content;

//    private List<Category> categoryList;
//    private List<String> categoryListString;

    private List<Long> categoryIdList;

    private String photo;

    private String photoUrl;
}
