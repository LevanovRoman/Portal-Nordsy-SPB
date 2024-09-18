package com.myapp.portalnordsyspb.repositories;

import com.myapp.portalnordsyspb.entities.Week;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeekRepository extends JpaRepository<Week, Long> {

    Week findTopByOrderByIdDesc();

    List<Week> findTop4ByOrderById();
}
