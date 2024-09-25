package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.AreaTableDto;
import com.myapp.portalnordsyspb.evaluationPU.entity.Area;
import com.myapp.portalnordsyspb.level5S.dto.Area5STableDto;
import com.myapp.portalnordsyspb.level5S.entity.Area5S;
import com.myapp.portalnordsyspb.level5S.repository.Area5SRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Area5SServiceImpl implements Area5SService{

    private final Area5SRepository area5SRepository;
    private final Result5SService result5SService;

    @Override
    public List<Area5STableDto> getListArea5STableDtoByDepartmentId(Long departmentId) {
        return area5SRepository.findAllByDepartmentId(departmentId)
                .stream()
                .map(this::convertArea5STableToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Area5S> getArea5SById(Long area_id) {
        return area5SRepository.findById(area_id);
    }

    private Area5STableDto convertArea5STableToDto(Area5S area) {
        return new Area5STableDto(
                area.getName(),
                result5SService.getListResultsByAreaIdForLastWeek(area.getId())
        );
    }
}
