package com.myapp.portalnordsyspb.service;

import com.myapp.portalnordsyspb.dto.DepartmentDto;
import com.myapp.portalnordsyspb.dto.DepartmentSiteDto;
import com.myapp.portalnordsyspb.entities.Department;
import com.myapp.portalnordsyspb.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;
    private final AreaService areaService;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public List<DepartmentSiteDto> getListDepartmentSite() {
        return departmentRepository.findAll()
                .stream()
                .map(this::convertDepartmentSiteToDto)
                .toList();
    }

    private DepartmentSiteDto convertDepartmentSiteToDto(Department department) {
        DepartmentSiteDto departmentSiteDto = new DepartmentSiteDto();
        departmentSiteDto.setDepartment(department.getNumber());
        departmentSiteDto.setAreaSiteDtoList(areaService
                .getListAreaSiteDtoByDepartmentId(department.getId()));
        return departmentSiteDto;
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
