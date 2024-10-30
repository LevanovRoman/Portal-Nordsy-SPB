package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.exceptions.DepartmentNotFoundException;
import com.myapp.portalnordsyspb.level5S.dto.request.Department5SRequestDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Department5SDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Department5SResponseDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Month5SAverageDto;
import com.myapp.portalnordsyspb.level5S.entity.Area5S;
import com.myapp.portalnordsyspb.level5S.entity.Department5S;
import com.myapp.portalnordsyspb.level5S.entity.Month5S;
import com.myapp.portalnordsyspb.level5S.repository.Area5SRepository;
import com.myapp.portalnordsyspb.level5S.repository.Department5SRepository;
import com.myapp.portalnordsyspb.level5S.repository.Month5SRepository;
import com.myapp.portalnordsyspb.level5S.repository.Result5SRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Month5SAverageDto getListLevelDoneForDepartment() {
        List<Department5S> department5SList = department5SRepository.findAll();
        Month5S month5S = month5SRepository.findMonthForLastMonth();
        long monthId = month5S.getId();
        String monthName = month5S.getName();
        List<Department5SDto> department5SDtoList = new ArrayList<>();
        for (Department5S department : department5SList){
            List<Area5S> area5SList = area5SRepository.findAllByDepartmentId(department.getId());
            OptionalDouble averageValue = area5SList
                    .stream()
                    .map(x->convertArea5SToLevelDone(x, monthId))
                    .mapToDouble(Integer::doubleValue)
                    .average();
            Double result = (double) (Math.ceil(averageValue.getAsDouble()*10)/10);
            department5SDtoList.add(new Department5SDto(department.getId(), "Цех " + department.getNumber(), result));
        }
        return new Month5SAverageDto(monthName, department5SDtoList);
    }

    @Override
    public void createDepartment5S(Department5SRequestDto department5SRequestDto) {
        Department5S department5S = new Department5S();
        department5S.setNumber(department5SRequestDto.number());
        department5SRepository.save(department5S);
    }

    @Override
    public void updateDepartment5S(Department5SRequestDto department5SRequestDto, long departmentId) {
        Department5S department5S = department5SRepository.findById(departmentId)
                .orElseThrow(()->new DepartmentNotFoundException("Department not found"));
        department5S.setNumber(department5SRequestDto.number());
        department5SRepository.save(department5S);
    }

    @Override
    public void deleteDepartment5S(long departmentId) {
        Department5S department5S = department5SRepository.findById(departmentId)
                .orElseThrow(()->new DepartmentNotFoundException("Department not found"));
        department5SRepository.delete(department5S);
    }

    @Override
    public List<Department5SResponseDto> getAllDepartment5S() {
        List<Department5S> department5SList = department5SRepository.findAll();
        return department5SList.stream().map(this::convertDepartment5SToDepartment5SResponse).toList();
    }

    private Department5SResponseDto convertDepartment5SToDepartment5SResponse(Department5S department5S) {
        return new Department5SResponseDto(department5S.getId(), department5S.getNumber());
    }

    private int convertArea5SToLevelDone(Area5S area5S, long monthId) {
        return result5SRepository
                .findByAreaIdAndMonthIdAndCriterionId(area5S.getId(), monthId, 1L)
                .getValue();
    }
}
