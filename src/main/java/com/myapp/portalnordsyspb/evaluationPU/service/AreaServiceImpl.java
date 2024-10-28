package com.myapp.portalnordsyspb.evaluationPU.service;

import com.myapp.portalnordsyspb.evaluationPU.dto.requestDto.AreaRequestDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.AreaDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.AreaTableDto;
import com.myapp.portalnordsyspb.evaluationPU.entity.Area;
import com.myapp.portalnordsyspb.evaluationPU.repository.AreaRepository;
import com.myapp.portalnordsyspb.evaluationPU.repository.DepartmentRepository;
import com.myapp.portalnordsyspb.exceptions.AreaNotFoundException;
import com.myapp.portalnordsyspb.exceptions.DepartmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService{

    private final AreaRepository areaRepository;
    private final DepartmentRepository departmentRepository;
    private final ResultService resultService;

    @Override
    public List<AreaTableDto> getListAreaTableDtoByDepartmentId(Long departmentId) {
        return areaRepository.findAllByDepartmentId(departmentId)
                .stream()
                .map(this::convertAreaTableToDto)
                .collect(Collectors.toList());
    }

//    @Override
//    public Optional<Area> getAreaById(Long area_id) {
//        return areaRepository.findById(area_id);
//    }

    @Override
    public List<AreaDto> getAreaDto() {
        return areaRepository.findAll()
                .stream().map(this::convertAreaToAreaDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createArea(AreaRequestDto areaRequestDto) {
        Area areaNew = new Area();
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

//    @Override
//    public AreaDto getAreaDtoById(Long id) {
//        Optional<Area> area = areaRepository.findById(id);
//        AreaDto areaDto = new AreaDto();
//        areaDto.setName(area.orElseThrow(()-> new AreaNotFoundException("Участок не найден.")).getName());
//        areaDto.setResultList(resultService.getResultListByAreaId(id));
//        return areaDto;
//    }

//    @Override
//    public List<AreaDto> getListAreasByDepartmentId(Long departmentId) {
//        return areaRepository.findAllByDepartmentId(departmentId)
//                .stream()
//                .map(this::convertAreaToDto)
//                .toList();
//    }

//    @Override
//    public List<AreaWeekDto> getListAreasByDepartmentIdAndWeek(Long departmentId, int weekNumber) {
//        return areaRepository.findAllByDepartmentId(departmentId)
//                .stream()
//                .map(x -> convertAreaToDtoWeek(x, weekNumber))
//                .toList();
//    }

//    @Override
//    public List<Area> createAreaWeekSet(List<AreaRequestDto> areaRequestDtoList) {
//        return areaRequestDtoList.stream()
//                .map(this::convertAreaRequestDto)
//                .toList();
//
//    }

//    private Area convertAreaRequestDto(AreaRequestDto areaRequestDto) {
//        Area area = new Area();
//        area.setId(areaRequestDto.areaId());
//        area.setName(areaRequestDto.name());
//        area.setResultList(resultService.createResultWeekSet(areaRequestDto.resultRequestDtoList()));
//        return area;
//    }

//    private AreaDto convertAreaToDto(Area area){
//        AreaDto areaDto = new AreaDto();
//        areaDto.setName(area.getName());
//        areaDto.setResultList(resultService.getListResultsByAreaId(area.getId()));
//        return areaDto;
//    }

//    private AreaWeekDto convertAreaToDtoWeek(Area area, int weekNumber){
//        AreaWeekDto areaWeekDto = new AreaWeekDto();
//        areaWeekDto.setName(area.getName());
//        areaWeekDto.setResultWeekList(resultService.getListResultsByAreaIdAndWeekNumber(area.getId(), weekNumber));
//        return areaWeekDto;
//    }
}
