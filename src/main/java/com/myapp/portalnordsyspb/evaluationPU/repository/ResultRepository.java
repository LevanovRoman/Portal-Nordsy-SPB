package com.myapp.portalnordsyspb.evaluationPU.repository;

import com.myapp.portalnordsyspb.evaluationPU.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findAllByAreaId(Long area_id);

    List<Result> findAllByAreaIdAndWeekId(Long area_id, Long weekNumber);

    Result findByAreaIdAndWeekIdAndCriterionId(Long area_id, Long week_id, Long criterion_id);

    List<Result> findAllByWeekId(long weekId);
}
