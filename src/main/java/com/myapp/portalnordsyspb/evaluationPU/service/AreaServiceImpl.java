package com.myapp.portalnordsyspb.evaluationPU.service;

import com.myapp.portalnordsyspb.evaluationPU.dto.requestDto.AreaRequestDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.AreaDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.AreaTableDto;
import com.myapp.portalnordsyspb.evaluationPU.entity.Area;
import com.myapp.portalnordsyspb.evaluationPU.entity.Criterion;
import com.myapp.portalnordsyspb.evaluationPU.entity.Result;
import com.myapp.portalnordsyspb.evaluationPU.entity.Week;
import com.myapp.portalnordsyspb.evaluationPU.repository.*;
import com.myapp.portalnordsyspb.exceptions.AreaNotFoundException;
import com.myapp.portalnordsyspb.exceptions.DepartmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService{

    private final AreaRepository areaRepository;
    private final DepartmentRepository departmentRepository;
    private final ResultService resultService;
    private final WeekRepository weekRepository;
    private final CriterionRepository criterionRepository;
    private final ResultRepository resultRepository;

    @Override
    public List<AreaTableDto> getListAreaTableDtoByDepartmentId(Long departmentId) {
        return areaRepository.findAllByDepartmentId(departmentId)
                .stream()
                .map(this::convertAreaTableToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AreaDto> getAreaDto() {
        return areaRepository.findAll()
                .stream().map(this::convertAreaToAreaDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createArea(AreaRequestDto areaRequestDto) {
        Area areaNew = new Area();
        List<Week> weeksLast4 = weekRepository.findTop4ByOrderByIdDesc();
        List<Criterion> criterionAll = criterionRepository.findAll();
        for (Week week : weeksLast4){
            for (Criterion criterion : criterionAll) {
                Result result = Result.builder().value(0).criterion(criterion).week(week).area(areaNew).build();
                resultRepository.save(result);
            }
        }
        saveArea(areaNew, areaRequestDto);
    }

    @Override
    public void updateArea(AreaRequestDto areaRequestDto, long areaId) {
        Area areaUpdate = areaRepository.findById(areaId)
                .orElseThrow(()-> new AreaNotFoundException("Area not found."));
        saveArea(areaUpdate, areaRequestDto);
    }

    @Override
    public void deleteArea(long areaId) {
        Area areaDelete = areaRepository.findById(areaId)
                .orElseThrow(()-> new AreaNotFoundException("Area not found."));
        areaRepository.delete(areaDelete);
    }

    private void saveArea(Area area, AreaRequestDto areaRequestDto){
        area.setName(areaRequestDto.name());
        area.setDepartment(departmentRepository.findById(areaRequestDto.departmentId())
                .orElseThrow(()-> new DepartmentNotFoundException("Department not found.")));
        areaRepository.save(area);
    }

    private AreaDto convertAreaToAreaDto(Area area) {
        return new AreaDto(
                area.getId(),
                area.getName(),
                area.getDepartment().getNumber()
        );
    }

    private AreaTableDto convertAreaTableToDto(Area area) {
        return new AreaTableDto(
                area.getName(),
                resultService.getListResultsByAreaIdForLastWeek(area.getId()),
                resultService.getListResultResultTotalFourWeeks(area.getId())
        );
    }
}
