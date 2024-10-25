package com.myapp.portalnordsyspb.evaluationPU.repository;

import com.myapp.portalnordsyspb.evaluationPU.entity.Week;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeekRepository extends JpaRepository<Week, Long> {

    Week findTopByOrderByIdDesc();

    List<Week> findTop4ByOrderByIdDesc();
}
