package com.myapp.portalnordsyspb.level5S.repository;

import com.myapp.portalnordsyspb.level5S.entity.Result5S;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Result5SRepository extends JpaRepository<Result5S, Long> {

    List<Result5S> findAllByAreaId(Long areaId);

//    List<Result5S> findAllByAreaIdAndMonthId(Long areaId, Long monthId);

    Result5S findByAreaIdAndMonthIdAndCriterionId(Long areaId, Long monthId, Long criterion_id);

    @Query(value = "SELECT * FROM result5s r WHERE r.month_id=:monthId AND r.criterion_id < 3", nativeQuery = true)
    List<Result5S> findAllByMonthIdAndCriterionIdLess3(@Param("monthId") long monthId);
}
