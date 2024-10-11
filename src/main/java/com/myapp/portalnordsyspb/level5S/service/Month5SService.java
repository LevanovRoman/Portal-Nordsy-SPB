package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.level5S.dto.request.Month5SRequestDto;
import com.myapp.portalnordsyspb.level5S.dto.request.Result5SRequestDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Month5SiteDto;

import java.util.List;

public interface Month5SService {
    List<Month5SiteDto> getListMonth5Site();
    void createMonth(Month5SRequestDto month5SRequestDto);
    void updateMonth(List<Result5SRequestDto> result5SRequestDtoList, long monthId);
    void deleteMonth(long monthId);
}


