package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.level5S.dto.response.Area5SDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Area5SiteDto;
import com.myapp.portalnordsyspb.level5S.entity.Area5S;
import com.myapp.portalnordsyspb.level5S.repository.Area5SRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Area5SServiceImpl implements Area5SService{

    private final Area5SRepository area5SRepository;
    private final Result5SService result5SService;

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
