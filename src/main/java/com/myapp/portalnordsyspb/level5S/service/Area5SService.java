package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.level5S.dto.response.Area5SDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Area5SiteDto;

import java.util.List;

public interface Area5SService {

    List<Area5SiteDto> getAllArea5SDto(Long month_id);

    List<Area5SDto> getListArea5SDto();
}
