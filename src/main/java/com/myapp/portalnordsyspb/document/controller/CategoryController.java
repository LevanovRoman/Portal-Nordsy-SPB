package com.myapp.portalnordsyspb.document.controller;

import com.myapp.portalnordsyspb.document.dto.response.CategoryResponseDto;
import com.myapp.portalnordsyspb.document.service.CategoryService;
import com.myapp.portalnordsyspb.library.dto.response.SectionResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Documents", description = "Description for documents")
@RequestMapping("/api/documents/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Получение всех категорий.")
    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
