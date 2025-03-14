package com.myapp.portalnordsyspb.level5S.repository;

import com.myapp.portalnordsyspb.level5S.entity.Month5S;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Month5SRepository extends JpaRepository<Month5S, Long> {

    @Query(value = "SELECT * FROM month5s WHERE id = (select MAX(id) FROM month5s)", nativeQuery = true)
    Month5S findMonthForLastMonth();

    List<Month5S> findTop12ByOrderByIdDesc();
}
