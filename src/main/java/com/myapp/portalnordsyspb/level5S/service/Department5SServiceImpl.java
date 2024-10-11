package com.myapp.portalnordsyspb.level5S.service;

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

//    @Override
//    @Cacheable(value = "Department5SService::getListDepartment5STable")
//    public List<Department5STableDto> getListDepartment5STable() {
//        return department5SRepository.findAll()
//                .stream()
//                .map(this::convertDepartment5STableToDto)
//                .collect(Collectors.toList());
//    }
//
//    private Department5STableDto convertDepartment5STableToDto(Department5S department5S) {
//        return new Department5STableDto(
//                department5S.getNumber(),
//                area5SService.getListArea5STableDtoByDepartmentId(department5S.getId())
//        );
//    }
}
