package com.myapp.portalnordsyspb.evaluationPU.repository;

import com.myapp.portalnordsyspb.evaluationPU.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

//    @Query(value = "SELECT d FROM department d JOIN portal.area a on d.id = a.department_id\n" +
//            "JOIN portal.result r on a.id = r.area_id\n" +
//            "JOIN portal.week w on w.id = r.week_id\n" +
//            "WHERE w.number=:weekNumber",
//            nativeQuery = true)
//    List<Department> findDepartmentsByWeekNumber(@Param("weekNumber") int weekNumber);

}
