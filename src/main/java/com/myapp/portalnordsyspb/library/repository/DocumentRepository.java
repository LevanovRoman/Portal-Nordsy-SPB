package com.myapp.portalnordsyspb.library.repository;

import com.myapp.portalnordsyspb.library.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
