package com.myapp.portalnordsyspb.evaluationPU.service;

import com.myapp.portalnordsyspb.evaluationPU.dto.requestDto.DepartmentRequestDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.AreaAndCriterionDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.DepartmentTableDto;
import com.myapp.portalnordsyspb.evaluationPU.entity.Department;
import com.myapp.portalnordsyspb.evaluationPU.repository.DepartmentRepository;
import com.myapp.portalnordsyspb.exceptions.DepartmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;
    private final AreaService areaService;
    private final CriterionService criterionService;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public List<DepartmentTableDto> getListDepartmentTable() {
        return departmentRepository.findAll()
                .stream()
                .map(this::convertDepartmentTableToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AreaAndCriterionDto getListAreaDtoAndCriterionDto() {
        return new AreaAndCriterionDto(
                areaService.getAreaDto(),
                criterionService.getCriterionDto()
        );
    }

    @Override
    public void createDepartment(DepartmentRequestDto departmentRequestDto) {
        Department department = new Department();
        department.setNumber(departmentRequestDto.number());
        departmentRepository.save(department);
    }

    @Override
    public void updateDepartment(DepartmentRequestDto departmentRequestDto, long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(()->new DepartmentNotFoundException("Department not found"));
        department.setNumber(departmentRequestDto.number());
        departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(()->new DepartmentNotFoundException("Department not found"));
        departmentRepository.delete(department);
    }

    private DepartmentTableDto convertDepartmentTableToDto(Department department) {
        return new DepartmentTableDto(
                department.getNumber(),
                areaService.getListAreaTableDtoByDepartmentId(department.getId())
        );
    }

}
