package com.myapp.portalnordsyspb.news.repository;

import com.myapp.portalnordsyspb.news.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String categoryString);
}
