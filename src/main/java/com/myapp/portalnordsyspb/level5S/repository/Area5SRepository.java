package com.myapp.portalnordsyspb.level5S.repository;

import com.myapp.portalnordsyspb.level5S.entity.Area5S;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Area5SRepository extends JpaRepository<Area5S, Long> {

    List<Area5S> findAllByDepartmentId(Long departmentId);
}
