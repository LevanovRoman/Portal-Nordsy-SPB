package com.myapp.portalnordsyspb.evaluationPU.repository;

import com.myapp.portalnordsyspb.evaluationPU.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findAllByAreaId(Long area_id);

//    List<Result> findAllByAreaIdAndWeekNumber(Long area_id, int weekNumber);

    List<Result> findAllByAreaIdAndWeekId(Long area_id, Long weekNumber);

//    List<Result> findAllByAreaIdAndCriterionId(Long area_id, Long criterion_id);

    Result findByAreaIdAndWeekIdAndCriterionId(Long area_id, Long week_id, Long criterion_id);

    List<Result> findAllByWeekId(long weekId);

//    void saveAllByWeekId(Long week_id, List<Result> resultList);

//    List<Result> findTop2ByOrderByAreaIdDesc(Long area_id);
}
