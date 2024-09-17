package com.myapp.portalnordsyspb.service;

import com.myapp.portalnordsyspb.dto.AreaDto;
import com.myapp.portalnordsyspb.dto.AreaWeekDto;
import com.myapp.portalnordsyspb.entities.Area;
import com.myapp.portalnordsyspb.exceptions.AreaNotFoundException;
import com.myapp.portalnordsyspb.repositories.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService{

    private final AreaRepository areaRepository;

    private final ResultService resultService;

//    @Override
//    public AreaDto getAreaDtoById(Long id) {
//        Optional<Area> area = areaRepository.findById(id);
//        AreaDto areaDto = new AreaDto();
//        areaDto.setName(area.orElseThrow(()-> new AreaNotFoundException("Участок не найден.")).getName());
//        areaDto.setResultList(resultService.getResultListByAreaId(id));
//        return areaDto;
//    }

    @Override
    public List<AreaDto> getListAreasByDepartmentId(Long departmentId) {
        return areaRepository.findAllByDepartmentId(departmentId)
                .stream()
                .map(this::convertAreaToDto)
                .toList();
    }

    @Override
    public List<AreaWeekDto> getListAreasByDepartmentIdAndWeek(Long departmentId, int weekNumber) {
        return areaRepository.findAllByDepartmentId(departmentId)
                .stream()
                .map(x -> convertAreaToDtoWeek(x, weekNumber))
                .toList();
    }

    private AreaDto convertAreaToDto(Area area){
        AreaDto areaDto = new AreaDto();
        areaDto.setName(area.getName());
        areaDto.setResultList(resultService.getListResultsByAreaId(area.getId()));
        return areaDto;
    }

    private AreaWeekDto convertAreaToDtoWeek(Area area, int weekNumber){
        AreaWeekDto areaWeekDto = new AreaWeekDto();
        areaWeekDto.setName(area.getName());
        areaWeekDto.setResultWeekList(resultService.getListResultsByAreaIdAndWeekNumber(area.getId(), weekNumber));
        return areaWeekDto;
    }
}
