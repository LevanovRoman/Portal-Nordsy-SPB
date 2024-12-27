package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.level5S.dto.request.Month5SRequestDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Area5SAndCriterion5SDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Month5SDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Month5SiteDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Result5SForMonthDto;

import java.util.List;

public interface Month5SService {
    List<Month5SiteDto> getListMonth5Site();

    void createMonth(Month5SRequestDto month5SRequestDto);

    void updateMonth(Month5SRequestDto month5SRequestDto, long monthId);

    void deleteMonth(long monthId);

    Area5SAndCriterion5SDto getListArea5SDtoAndCriterion5SDto();

    List<Month5SDto> getAllMonth5sDto();

    List<Result5SForMonthDto> getAllResult5SForMonthDto(long monthId);
}


