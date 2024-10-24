package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.level5S.dto.response.Department5SDto;
import com.myapp.portalnordsyspb.level5S.entity.Area5S;
import com.myapp.portalnordsyspb.level5S.entity.Department5S;
import com.myapp.portalnordsyspb.level5S.repository.Area5SRepository;
import com.myapp.portalnordsyspb.level5S.repository.Department5SRepository;
import com.myapp.portalnordsyspb.level5S.repository.Month5SRepository;
import com.myapp.portalnordsyspb.level5S.repository.Result5SRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class Department5SServiceImpl implements Department5SService {

    private final Department5SRepository department5SRepository;
    private final Area5SRepository area5SRepository;
    private final Month5SRepository month5SRepository;
    private final Result5SRepository result5SRepository;


    @Override
    public List<Department5SDto> getListLevelDoneForDepartment() {
        List<Department5S> department5SList = department5SRepository.findAll();
        long monthId = month5SRepository.findMonthIdForLastMonth();
        List<Department5SDto> department5SDtoList = new ArrayList<>();
        for (Department5S department : department5SList){
            List<Area5S> area5SList = area5SRepository.findAllByDepartmentId(department.getId());
            OptionalDouble averageValue = area5SList
                    .stream()
                    .map(x->convertArea5SToLevelDone(x, monthId))
                    .mapToDouble(Integer::doubleValue)
                    .average();
            System.out.println(averageValue);
            Double result = (double) (Math.ceil(averageValue.getAsDouble()*10)/10);
            System.out.println(result);
            department5SDtoList.add(new Department5SDto(department.getNumber(), result));
        }
        return department5SDtoList;
    }

    private int convertArea5SToLevelDone(Area5S area5S, long monthId) {
        return result5SRepository
                .findByAreaIdAndMonthIdAndCriterionId(area5S.getId(), monthId, 1L)
                .getValue();
    }
}
