package com.myapp.portalnordsyspb.level5S.repository;

import com.myapp.portalnordsyspb.level5S.entity.Result5S;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Result5SRepository extends JpaRepository<Result5S, Long> {

    List<Result5S> findAllByAreaId(Long areaId);

//    List<Result5S> findAllByAreaIdAndMonthId(Long areaId, Long monthId);

    Result5S findByAreaIdAndMonthIdAndCriterionId(Long areaId, Long monthId, Long criterion_id);
}
