package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.level5S.dto.Department5STableDto;
import com.myapp.portalnordsyspb.level5S.dto.test.Month5SiteDto;
import com.myapp.portalnordsyspb.level5S.entity.Department5S;
import com.myapp.portalnordsyspb.level5S.entity.Month5S;
import com.myapp.portalnordsyspb.level5S.repository.Area5SRepository;
import com.myapp.portalnordsyspb.level5S.repository.Month5SRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Month5SServiceImpl implements Month5SService{

    private final Month5SRepository month5SRepository;
    private final Area5SService area5SService;

    @Override
    public List<Month5SiteDto> getListMonth5Site() {
        return month5SRepository.findAll()
                .stream()
                .map(this::convertMonth5SToDto)
                .collect(Collectors.toList());
    }

    private Month5SiteDto convertMonth5SToDto(Month5S month5S) {
        return new Month5SiteDto(
                month5S.getName(),
                area5SService.getAllArea5SDto(month5S.getId())
        );
    }
}