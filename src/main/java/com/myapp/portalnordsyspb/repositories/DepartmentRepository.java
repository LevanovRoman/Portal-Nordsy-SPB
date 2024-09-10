package com.myapp.portalnordsyspb.repositories;

import com.myapp.portalnordsyspb.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
