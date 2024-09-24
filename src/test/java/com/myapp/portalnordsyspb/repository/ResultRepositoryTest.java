package com.myapp.portalnordsyspb.repository;

import com.myapp.portalnordsyspb.evaluationPU.entity.*;
import com.myapp.portalnordsyspb.evaluationPU.repository.DepartmentRepository;
import com.myapp.portalnordsyspb.evaluationPU.repository.ResultRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ResultRepositoryTest {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void DepartmentRepository_Save_returnSavedDepartment(){
        Department department = new Department();
        department.setNumber(1);

        Department savedDepartment = departmentRepository.save(department);

        Assertions.assertThat(savedDepartment).isNotNull();
    }
    @Test
    public void ResultRepository_SaveAll_returnSavedResult() {
        // Arrange
        Result result1 = new Result();
        Department department = new Department();
        department.setNumber(1);
        Area area = new Area();
        area.setName("test-area");
        area.setDepartment(department);
        Criterion criterion = new Criterion();
        criterion.setName("test-criterion");
        Week week = new Week();
        week.setNumber(1);
        result1.setArea(area);
        result1.setCriterion(criterion);
        result1.setWeek(week);
        result1.setValue(99);

        Result result2 = new Result();
        result2.setArea(area);
        result2.setCriterion(criterion);
        result2.setWeek(week);
        result2.setValue(99);

//        Result result3 = Result.builder()
//                .area(area)
//                .criterion(criterion)
//                .week(week)
//                .value(88)
//                .build();
//        List<Result> resultList = List.of(result1, result2);

        // Act
//        List<Result> savedResult = resultRepository.saveAll(resultList);

        Result savedResult = resultRepository.save(result1);
        // Assert
        Assertions.assertThat(savedResult).isNotNull();
//        Assertions.assertThat(savedResult.size()).isEqualTo(2);
    }

}
