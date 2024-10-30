package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.level5S.dto.request.Area5SRequestDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Area5SDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Area5SiteDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Maxvalue5SResponseDto;

import java.util.List;

public interface Area5SService {

    List<Area5SiteDto> getAllArea5SDto(Long month_id);

    List<Area5SDto> getListArea5SDto();

    void createArea5S(Area5SRequestDto area5SRequestDto);

    void updateArea5S(Area5SRequestDto area5SRequestDto, long areaId);

    void deleteArea5S(long areaId);

    List<Maxvalue5SResponseDto> getAllMaxvalue();
}
