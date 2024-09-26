package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.level5S.dto.test.Area5SiteDto;
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

//    @Override
//    public List<Area5STableDto> getListArea5STableDtoByDepartmentId(Long departmentId) {
//        return area5SRepository.findAllByDepartmentId(departmentId)
//                .stream()
//                .map(this::convertArea5STableToDto)
//                .collect(Collectors.toList());
//    }

//    @Override
//    public Optional<Area5S> getArea5SById(Long area_id) {
//        return area5SRepository.findById(area_id);
//    }

    @Override
    public List<Area5SiteDto> getAllArea5SDto(Long month_id) {
        return area5SRepository.findAll()
                .stream()
                .map(area -> convertArea5SToDto(area, month_id))
                .collect(Collectors.toList());
    }

    private Area5SiteDto convertArea5SToDto(Area5S area, Long monthId) {
        return new Area5SiteDto(
                area.getName(),
                area.getDepartment().getNumber(),
                result5SService.getResult5SiteDtoByMonthIdAndAreaId(monthId, area.getId())
        );
    }

//    private Area5STableDto convertArea5STableToDto(Area5S area) {
//        return new Area5STableDto(
//                area.getName(),
//                result5SService.getListResultsByAreaIdForLastWeek(area.getId())
//        );
//    }
}
