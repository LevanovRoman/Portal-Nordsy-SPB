package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.evaluationPU.dto.requestDto.AreaRequestDto;
import com.myapp.portalnordsyspb.evaluationPU.entity.Area;
import com.myapp.portalnordsyspb.exceptions.AreaNotFoundException;
import com.myapp.portalnordsyspb.exceptions.DepartmentNotFoundException;
import com.myapp.portalnordsyspb.exceptions.MaxvalueNotFoundException;
import com.myapp.portalnordsyspb.level5S.dto.request.Area5SRequestDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Area5SDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Area5SiteDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Maxvalue5SResponseDto;
import com.myapp.portalnordsyspb.level5S.entity.*;
import com.myapp.portalnordsyspb.level5S.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Area5SServiceImpl implements Area5SService{

    private final Area5SRepository area5SRepository;
    private final Result5SService result5SService;
    private final Department5SRepository department5SRepository;
    private final Month5SRepository month5SRepository;
    private final Criterion5SRepository criterion5SRepository;
    private final Result5SRepository result5SRepository;
    private final Maxvalue5SRepository maxvalue5SRepository;

    @Override
    public List<Area5SiteDto> getAllArea5SDto(Long month_id) {
        return area5SRepository.findAll()
                .stream()
                .map(area -> convertArea5SToDto(area, month_id))
                .collect(Collectors.toList());
    }

    @Override
    public List<Area5SDto> getListArea5SDto() {
        return area5SRepository.findAll()
                .stream().map(this::convertArea5SToArea5SDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createArea5S(Area5SRequestDto area5SRequestDto) {
        Area5S area5SNew = new Area5S();
        List<Month5S> monthsLast12 = month5SRepository.findTop12ByOrderByIdDesc();
        List<Criterion5S> criterion5SAll = criterion5SRepository.findAll();
        saveArea5S(area5SNew, area5SRequestDto);
        for (Month5S month5S : monthsLast12){
            for (Criterion5S criterion5S : criterion5SAll){
                Result5S result5S;
                if (criterion5S.getId() == 3){
                    result5S = Result5S
                            .builder()
                            .area(area5SNew)
                            .month(month5S)
                            .criterion(criterion5S)
                            .value(area5SNew.getMaxvalue().getValue())
                            .build();
                } else {
                    result5S = Result5S
                            .builder()
                            .area(area5SNew)
                            .month(month5S)
                            .criterion(criterion5S)
                            .value(0)
                            .build();
                }
                result5SRepository.save(result5S);
            }
        }
    }

    @Override
    public void updateArea5S(Area5SRequestDto area5SRequestDto, long areaId) {
        Area5S area5SUpdate = area5SRepository.findById(areaId)
                .orElseThrow(()-> new AreaNotFoundException("Area not found."));
        saveArea5S(area5SUpdate, area5SRequestDto);
    }

    @Override
    public void deleteArea5S(long areaId) {
        Area5S area5SDelete = area5SRepository.findById(areaId)
                .orElseThrow(()-> new AreaNotFoundException("Area not found."));
        area5SRepository.delete(area5SDelete);
    }

    @Override
    public List<Maxvalue5SResponseDto> getAllMaxvalue() {
        List<Maxvalue5S> maxvalue5SList = maxvalue5SRepository.findAll();
        return maxvalue5SList.stream().map(this::convertMaxvalueToMaxvalueResponseDto).toList();
    }

    private Maxvalue5SResponseDto convertMaxvalueToMaxvalueResponseDto(Maxvalue5S maxvalue5S) {
        return new Maxvalue5SResponseDto(
                maxvalue5S.getId(), maxvalue5S.getValue()
        );
    }

    private void saveArea5S(Area5S area5s, Area5SRequestDto area5SRequestDto){
        area5s.setName(area5SRequestDto.name());
        area5s.setDepartment(department5SRepository.findById(area5SRequestDto.departmentId())
                .orElseThrow(()-> new DepartmentNotFoundException("Department not found.")));
        area5s.setMaxvalue(maxvalue5SRepository.findById(area5SRequestDto.maxvalueId())
                .orElseThrow(()-> new MaxvalueNotFoundException("Max value not found")));
        area5SRepository.save(area5s);
    }

    private Area5SDto convertArea5SToArea5SDto(Area5S area5S) {
        return new Area5SDto(area5S.getId(), area5S.getName(), area5S.getDepartment().getNumber());
    }

    private Area5SiteDto convertArea5SToDto(Area5S area, Long monthId) {
        return new Area5SiteDto(
                area.getName(),
                area.getDepartment().getNumber(),
                result5SService.getResult5SiteDtoByMonthIdAndAreaId(monthId, area.getId())
        );
    }
}
