package com.myapp.portalnordsyspb.service;

import com.myapp.portalnordsyspb.dto.AreaDto;
import com.myapp.portalnordsyspb.entities.Area;
import com.myapp.portalnordsyspb.repositories.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService{

    private final AreaRepository areaRepository;

    private final ResultService resultService;

    @Override
    public AreaDto getAreaDtoById(Long id) {
        Optional<Area> area = areaRepository.findById(id);
        AreaDto areaDto = new AreaDto();
        areaDto.setName(area.get().getName());
        areaDto.setResultList(resultService.getResultListByAreaId(id));
        return areaDto;
    }
}
