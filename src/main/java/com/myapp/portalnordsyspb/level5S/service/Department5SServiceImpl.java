package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.DepartmentTableDto;
import com.myapp.portalnordsyspb.evaluationPU.entity.Department;
import com.myapp.portalnordsyspb.level5S.dto.Department5STableDto;
import com.myapp.portalnordsyspb.level5S.entity.Department5S;
import com.myapp.portalnordsyspb.level5S.repository.Department5SRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Department5SServiceImpl implements Department5SService{

    private final Department5SRepository department5SRepository;
    private final Area5SService area5SService;

    @Override
    public List<Department5S> getAllDepartments() {
        return department5SRepository.findAll();
    }

    @Override
    public List<Department5STableDto> getListDepartmentTable() {
        return department5SRepository.findAll()
                .stream()
                .map(this::convertDepartment5STableToDto)
                .toList();
    }

    private Department5STableDto convertDepartment5STableToDto(Department5S department5S) {
        return new Department5STableDto(
                department5S.getNumber(),
                area5SService.getListArea5STableDtoByDepartmentId(department5S.getId())
        );
    }
}
