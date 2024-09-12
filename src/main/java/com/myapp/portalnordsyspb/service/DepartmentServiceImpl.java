package com.myapp.portalnordsyspb.service;

import com.myapp.portalnordsyspb.dto.DepartmentDto;
import com.myapp.portalnordsyspb.dto.ResultDto;
import com.myapp.portalnordsyspb.entities.Area;
import com.myapp.portalnordsyspb.entities.Department;
import com.myapp.portalnordsyspb.entities.Result;
import com.myapp.portalnordsyspb.repositories.AreaRepository;
import com.myapp.portalnordsyspb.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;
    private final AreaRepository areaRepository;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public List<DepartmentDto> getListDepartments() {

        List<Department> departmentList = departmentRepository.findAll();
        List<DepartmentDto> departmentDtoList = new ArrayList<>();
        for (Department department : departmentList){
            DepartmentDto departmentDto = new DepartmentDto();
            departmentDto.setDepartment(department.getNumber());
            List<Area> areaList = areaRepository.findByDepartmentId(department.getId());
            departmentDto.setArea(areaList);
            departmentDtoList.add(departmentDto);
        }
        return departmentDtoList;
    }
}
