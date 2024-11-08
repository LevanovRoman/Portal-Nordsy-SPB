package com.myapp.portalnordsyspb.evaluationPU.repository;

import com.myapp.portalnordsyspb.evaluationPU.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {

    List<Area> findAllByDepartmentId(Long departmentId);
}
