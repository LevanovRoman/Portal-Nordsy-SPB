package com.myapp.portalnordsyspb.repositories;

import com.myapp.portalnordsyspb.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query(value = "SELECT d FROM department d JOIN portal.area a on d.id = a.department_id\n" +
            "JOIN portal.result r on a.id = r.area_id\n" +
            "JOIN portal.week w on w.id = r.week_id\n" +
            "WHERE w.number=:weekNumber",
            nativeQuery = true)
    List<Department> findDepartmentsByWeekNumber(@Param("weekNumber") int weekNumber);

}
