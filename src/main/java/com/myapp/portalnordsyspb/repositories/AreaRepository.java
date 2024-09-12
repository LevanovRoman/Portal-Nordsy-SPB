package com.myapp.portalnordsyspb.repositories;

import com.myapp.portalnordsyspb.entities.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {
    List<Area> findByDepartmentId(Long id);
}
