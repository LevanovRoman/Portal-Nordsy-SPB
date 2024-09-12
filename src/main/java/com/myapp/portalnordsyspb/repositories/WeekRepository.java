package com.myapp.portalnordsyspb.repositories;

import com.myapp.portalnordsyspb.dto.ResponseWeek;
import com.myapp.portalnordsyspb.dto.WeekDto;
import com.myapp.portalnordsyspb.entities.Result;
import com.myapp.portalnordsyspb.entities.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeekRepository extends JpaRepository<Week, Long> {

    Week findByNumber(int number);

//    @Query(value = "SELECT DISTINCT * FROM question q WHERE q.category_id=:categoryId ORDER BY RAND() LIMIT :numQ",
//            nativeQuery = true)
//    List<Question> findRandomQuestionsByCategory(@Param("categoryId") int categoryId, @Param("numQ") int numQ);
//
//    @Query(value = "select d.number, a.name, c.name, w.number, r.value from week w\n" +
//            "    full join result r on w.id = r.week_id\n" +
//            "    full join area_criterion ca on r.area_criterion_id = ca.id\n" +
//            "    full join criterion c on ca.criterion_id = c.id\n" +
//            "    full join public.area a on a.id = ca.area_id\n" +
//            "    full join public.department d on a.department_id = d.id\n" +
//            "    where w.number=:weekNum", nativeQuery = true)
//    List<WeekDto> findResponseByWeekNumber(@Param("weekNum") int weekNum);

    @Query(value = "select r from week w\n" +
            "    full join result r on w.id = r.week_id\n" +
            "    full join area_criterion ca on r.area_criterion_id = ca.id\n" +
            "    full join criterion c on ca.criterion_id = c.id\n" +
            "    full join public.area a on a.id = ca.area_id\n" +
            "    full join public.department d on a.department_id = d.id\n" +
            "    where w.number=:weekNum", nativeQuery = true)
    List<Result> findResponseByWeekNumber(@Param("weekNum") int weekNum);



}
