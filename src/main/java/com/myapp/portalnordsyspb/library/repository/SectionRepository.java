package com.myapp.portalnordsyspb.library.repository;

import com.myapp.portalnordsyspb.library.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section, Long> {
}
