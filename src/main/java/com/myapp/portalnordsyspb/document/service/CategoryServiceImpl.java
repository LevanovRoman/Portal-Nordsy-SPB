package com.myapp.portalnordsyspb.document.service;

import com.myapp.portalnordsyspb.document.dto.response.CategoryResponseDto;
import com.myapp.portalnordsyspb.document.entity.Category;
import com.myapp.portalnordsyspb.document.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository
                .findAll()
                .stream().map(this::convertCategoryToCategoryResponseDto).toList();
    }

    private CategoryResponseDto convertCategoryToCategoryResponseDto(Category category) {
        return CategoryResponseDto
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
