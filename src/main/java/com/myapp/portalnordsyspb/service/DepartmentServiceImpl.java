package com.myapp.portalnordsyspb.service;

import com.myapp.portalnordsyspb.dto.DepartmentDto;
import com.myapp.portalnordsyspb.dto.DepartmentSiteDto;
import com.myapp.portalnordsyspb.dto.DepartmentWeekDto;
import com.myapp.portalnordsyspb.dto.requestDto.TotalWeekSetDto;
import com.myapp.portalnordsyspb.entities.Department;
import com.myapp.portalnordsyspb.repositories.AreaRepository;
import com.myapp.portalnordsyspb.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;
    private final AreaRepository areaRepository;
    private final AreaService areaService;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public List<DepartmentDto> getListDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(this::convertDepartmentToDto)
                .toList();
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
//    public List<DepartmentWeekDto> getListDepartmentsByWeek(int weekNumber) {
//        return departmentRepository.findAll()
//                .stream()
//                .map(x -> convertDepartmentToDtoWeek(x, weekNumber))
//                .collect(Collectors.toList());
//    }

//    @Override
//    public void addTotalWeekSet(int weekNumber, TotalWeekSetDto totalWeekSetDto) {
//
//    }

    private DepartmentDto convertDepartmentToDto(Department department){
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartment(department.getNumber());
        departmentDto.setArea(areaService.getListAreasByDepartmentId(department.getId()));
        return departmentDto;
    }

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
