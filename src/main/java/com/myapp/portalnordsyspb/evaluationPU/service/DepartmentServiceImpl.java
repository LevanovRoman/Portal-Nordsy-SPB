package com.myapp.portalnordsyspb.evaluationPU.service;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.AreaAndCriterionDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.DepartmentTableDto;
import com.myapp.portalnordsyspb.evaluationPU.entity.Department;
import com.myapp.portalnordsyspb.evaluationPU.repository.DepartmentRepository;
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

    private DepartmentTableDto convertDepartmentTableToDto(Department department) {
        return new DepartmentTableDto(
                department.getNumber(),
                areaService.getListAreaTableDtoByDepartmentId(department.getId())
        );
    }

//    @Override
//    public List<DepartmentDto> getListDepartments() {
//        return departmentRepository.findAll()
//                .stream()
//                .map(this::convertDepartmentToDto)
//                .toList();
//    }

//    @Override
//    public void createTotalWeekSet(TotalWeekSetDto totalWeekSetDto) {
//        List<Department> departmentList = totalWeekSetDto.departmentRequestDtoList()
//                .stream()
//                .map(this::convertDepartmentRequestDto)
//                .map(departmentRepository::save)
//                .toList();
//
//    }

//    private Department convertDepartmentRequestDto(DepartmentRequestDto departmentRequestDto) {
//        Department department = new Department();
//        department.setId(departmentRequestDto.departmentId());
//        department.setNumber(departmentRequestDto.number());
//        department.setAreaList(areaService.createAreaWeekSet(departmentRequestDto.areaRequestDtoList()));
//        return department;
//    }

//    @Override
//    public void createDepartmentSet(DepartmentRequestDto departmentRequestDto) {
//        Department department = new Department();
//
//    }

//    @Override
//    public List<DepartmentWeekDto> getListDepartmentsByWeek(int weekNumber) {
//        return departmentRepository.findAll()
//                .stream()
//                .map(x -> convertDepartmentToDtoWeek(x, weekNumber))
//                .collect(Collectors.toList());
//    }

//    private DepartmentDto convertDepartmentToDto(Department department){
//        DepartmentDto departmentDto = new DepartmentDto();
//        departmentDto.setDepartment(department.getNumber());
//        departmentDto.setArea(areaService.getListAreasByDepartmentId(department.getId()));
//        return departmentDto;
//    }

//    private DepartmentWeekDto convertDepartmentToDtoWeek(Department department, int weekNumber){
//        DepartmentWeekDto departmentWeekDto = new DepartmentWeekDto();
//        departmentWeekDto.setDepartment(department.getNumber());
//        departmentWeekDto.setWeekNumber(weekNumber);
//        departmentWeekDto.setArea(areaService.getListAreasByDepartmentIdAndWeek(department.getId(), weekNumber));
//        return departmentWeekDto;
//    }

//    @Override
//    public List<DepartmentDto> getListDepartments() {
//
//        List<Department> departmentList = departmentRepository.findAll();
//        List<DepartmentDto> departmentDtoList = new ArrayList<>();
//        for (Department department : departmentList){
//            DepartmentDto departmentDto = new DepartmentDto();
//            departmentDto.setDepartment(department.getNumber());
//            List<Area> areaList = areaRepository.findByDepartmentId(department.getId());
//            List<AreaDto> areaDtoList = new ArrayList<>();
//            for (Area area : areaList){
//                AreaDto areaDto = areaService.getAreaDtoById(area.getId());
//                areaDtoList.add(areaDto);
//            }
//            departmentDto.setArea(areaDtoList);
//            departmentDtoList.add(departmentDto);
//        }
//        return departmentDtoList;
//    }
}
