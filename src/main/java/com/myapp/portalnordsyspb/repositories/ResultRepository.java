package com.myapp.portalnordsyspb.repositories;

import com.myapp.portalnordsyspb.entities.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findAllByAreaId(Long area_id);
}
