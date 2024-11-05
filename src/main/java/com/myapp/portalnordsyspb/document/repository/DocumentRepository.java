package com.myapp.portalnordsyspb.document.repository;

import com.myapp.portalnordsyspb.document.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findAllByCategoryId(long categoryId);
}
