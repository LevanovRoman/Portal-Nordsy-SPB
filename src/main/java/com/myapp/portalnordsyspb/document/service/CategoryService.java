package com.myapp.portalnordsyspb.document.service;

import com.myapp.portalnordsyspb.document.dto.response.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> getAllCategories();
}
